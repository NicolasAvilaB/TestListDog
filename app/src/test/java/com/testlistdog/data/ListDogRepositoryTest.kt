package com.testlistdog.data

import com.testlistdog.data.models.RemoteDogRandomImage
import com.testlistdog.data.models.RemoteListDog
import com.testlistdog.data.source.ListDogSourceRemote
import com.testlistdog.factory.DogRandomImageFactory.makeRemoteDogRandomImage
import com.testlistdog.factory.ListDogFactory.makeRemoteListDog
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
internal class ListDogRepositoryTest{

    @Test
    fun `given remote list dog, when getListsDog called, then emits remote list dog`() = runBlocking {
        val expectedListDog = makeRemoteListDog()
        val remote = mockk<ListDogSourceRemote> {
            coEvery { getListDogRemote() } returns expectedListDog
        }
        val repository = ListDogRepository(remote)
        val result = mutableListOf<RemoteListDog>()
        repository.getListsDog().collect {
            result.add(it)
        }
        assertEquals(1, result.size)
        assertEquals(expectedListDog, result[0])
    }

    @Test
    fun `given remote dog image, when getDogImages called, then emits remote dog random image`() =  runBlocking {
        val expectedDogImage = makeRemoteDogRandomImage()
        val remote = mockk<ListDogSourceRemote> {
            coEvery { getDogImageRemote() } returns expectedDogImage
        }
        val repository = ListDogRepository(remote)
        val result = mutableListOf<RemoteDogRandomImage>()
        repository.getDogImages().collect {
            result.add(it)
        }
        assertEquals(1, result.size)
        assertEquals(expectedDogImage, result[0])
    }
}