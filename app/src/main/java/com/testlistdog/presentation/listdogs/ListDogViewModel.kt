package com.testlistdog.presentation.listdogs

import androidx.lifecycle.ViewModel
import com.testlistdog.data.ListDogRepository
import com.testlistdog.data.models.RemoteListDog
import com.testlistdog.presentation.listdogs.events.ListDogUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.take
import org.json.JSONArray

internal class ListDogViewModel(
    private val repository: ListDogRepository
) : ViewModel() {

    fun getListDogWithImages(): Flow<ListDogUiState> = flow {

            val listDog = repository.getListsDog().first()
            val dogNames = extractDogNames(listDog).take(10)
            val imageStates = mutableListOf<String>()

            dogNames.map { dogName ->
                val randomImage = repository.getDogImages(dogName).take(1).firstOrNull()
                randomImage?.let { imageState ->
                    imageStates.add(imageState.message)
                }
            }
        emit(ListDogUiState.DisplayListDogUiState(dogNames, imageStates))

    }.catch {  e ->
        ListDogUiState.ErrorUiState(e)
    }.flowOn(Dispatchers.IO)

    private fun extractDogNames(listDog: RemoteListDog): List<String> {
        val dogNames = mutableListOf<String>()
        listDog.message?.let {
            val jsonArray = JSONArray(it)
            for (i in 0 until jsonArray.length()) {
                val name = jsonArray.getString(i)
                dogNames.add(name)
            }
        }
        return dogNames
    }
}
