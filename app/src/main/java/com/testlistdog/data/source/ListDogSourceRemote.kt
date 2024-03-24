package com.testlistdog.data.source

import com.testlistdog.data.models.RemoteDogRandomImage
import com.testlistdog.data.models.RemoteListDog

internal interface ListDogSourceRemote {
    suspend fun getListDogRemote(): RemoteListDog
    suspend fun getDogImageRemote(): RemoteDogRandomImage

}
