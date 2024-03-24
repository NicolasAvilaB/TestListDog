package com.testlistdog.presentation.listdogs.events

internal sealed class ListDogUiState {
    object LoadingUiState : ListDogUiState()
    data class DisplayListDogUiState(
        val listDog: List<String>,
        val listDogImages: List<String>
    ) : ListDogUiState()

    data class ErrorUiState(val error: Throwable) : ListDogUiState()
}