package com.testlistdog.data.remote

import com.testlistdog.data.models.RemoteDogRandomImage
import com.testlistdog.data.models.RemoteListDog
import com.testlistdog.data.remote.retrofit.ListDogWebService
import com.testlistdog.data.source.ListDogSourceRemote

internal class ListDogRemoteImpl(
    private val webService: ListDogWebService
) : ListDogSourceRemote {
    
    override suspend fun getListDogRemote(): RemoteListDog =
        webService.getListDog()

    override suspend fun getDogImageRemote(): RemoteDogRandomImage =
        webService.getDogRandomImage()

}