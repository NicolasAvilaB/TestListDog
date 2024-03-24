package com.testlistdog.ui.di

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.testlistdog.data.ListDogRepository

internal class ListDogFactory (
    private val repository: ListDogRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            ListDogRepository::class.java
        ).newInstance(repository)
    }
}

internal inline fun <reified T : ViewModel> getFactoryViewModel(
    activity: ComponentActivity,
    repository: ListDogRepository
): T {
    return ViewModelProvider(
        activity,
        ListDogFactory(
            repository = repository
        )
    )[T::class.java]
}