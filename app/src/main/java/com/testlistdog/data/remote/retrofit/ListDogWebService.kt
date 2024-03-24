package com.testlistdog.data.remote.retrofit

import com.testlistdog.data.models.RemoteDogRandomImage
import com.testlistdog.data.models.RemoteListDog
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

internal interface ListDogWebService {
    @GET("api/breeds/list")
    suspend fun getListDog(): RemoteListDog

    @GET("api/breeds/image/random")
    suspend fun getDogRandomImage(): RemoteDogRandomImage

}
