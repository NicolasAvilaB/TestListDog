package com.testlistdog.data

import com.testlistdog.data.models.RemoteDogRandomImage
import com.testlistdog.data.models.RemoteListDog
import com.testlistdog.data.source.ListDogSourceRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class ListDogRepository(
    private val remote: ListDogSourceRemote
) {
    fun getListsDog(): Flow<RemoteListDog> = flow {
        val listDog = remote.getListDogRemote()
        emit(listDog)
    }

    fun getDogImages(dogName: String): Flow<RemoteDogRandomImage> = flow {
        val randomDogImages = remote.getDogImageRemote()
        emit(randomDogImages)
    }

}
