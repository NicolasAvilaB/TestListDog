package com.testlistdog.factory

import com.testlistdog.data.models.RemoteListDog

internal object ListDogFactory {
    fun makeRemoteListDog() = RemoteListDog(
        message = listOf(),
        status = toString()
    )
}