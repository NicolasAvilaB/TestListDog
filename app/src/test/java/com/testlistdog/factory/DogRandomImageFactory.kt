package com.testlistdog.factory

import com.testlistdog.data.models.RemoteDogRandomImage

internal object DogRandomImageFactory {
    fun makeRemoteDogRandomImage() = RemoteDogRandomImage(
        message = toString(),
        status = toString()
    )
}