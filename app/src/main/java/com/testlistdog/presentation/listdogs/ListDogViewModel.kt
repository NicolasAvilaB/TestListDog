package com.testlistdog.presentation.listdogs

import android.util.Log
import androidx.lifecycle.ViewModel
import com.testlistdog.data.ListDogRepository
import com.testlistdog.data.models.RemoteListDog
import com.testlistdog.presentation.listdogs.events.ListDogUiState
import com.testlistdog.presentation.listdogs.events.ListDogUiState.LoadingUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.singleOrNull
import org.json.JSONArray
import org.json.JSONObject

internal class ListDogViewModel(
    private val repository: ListDogRepository
) : ViewModel() {

    fun getListDogWithImages(): Flow<ListDogUiState> = flow<ListDogUiState> {

        repository.getListsDog()
            .map { listDog ->
                val dogNames = mutableListOf<String>()
                listDog.forEach { remoteDog ->
                    remoteDog.message?.let {
                        dogNames.add(it)
                    }
                }
                dogNames
            }
            .flatMapConcat { dogNames ->
                repository.getDogImages()
                    .map { randomImage ->
                        dogNames to randomImage
                    }
            }
            .collect { (dogNames, randomImage) ->
                var availableImages = randomImage.message
                emit(ListDogUiState.DisplayListDogUiState(dogNames, availableImages))
            }
    }.catch { emit(ListDogUiState.ErrorUiState(it)) }

}
