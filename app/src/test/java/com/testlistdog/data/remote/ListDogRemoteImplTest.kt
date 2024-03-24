package com.testlistdog.data.remote

import com.testlistdog.data.remote.retrofit.ListDogWebService
import com.testlistdog.factory.DogRandomImageFactory.makeRemoteDogRandomImage
import com.testlistdog.factory.ListDogFactory.makeRemoteListDog
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ListDogRemoteImplTest {
    private val webService = mockk<ListDogWebService>()

    @Test
    fun `when getListDogRemote is called then return RemoteListDog`() = runBlocking {
        val remoteListDog = makeRemoteListDog()
        coEvery { webService.getListDog() } returns remoteListDog

        val result = webService.getListDog()

        assertEquals(result, remoteListDog)
    }

    @Test
    fun `when getDogImageRemote is called then return RemoteDogRandomImage`() = runBlocking {
        val remoteDogImage = makeRemoteDogRandomImage()
        coEvery { webService.getDogRandomImage() } returns remoteDogImage

        val result = webService.getDogRandomImage()

        assertEquals(result, remoteDogImage)
    }
}