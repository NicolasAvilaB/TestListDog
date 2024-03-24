package com.testlistdog.ui.listdogs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.testlistdog.presentation.listdogs.ListDogViewModel
import com.testlistdog.presentation.listdogs.events.ListDogUiState
import com.testlistdog.ui.listdogs.components.DogItem

@Composable
internal fun ListDogScreen(
    viewModel: ListDogViewModel
) {
    val context = LocalContext.current

    val uiState = remember {
        viewModel.getListDogWithImages()
    }.collectAsState(initial = ListDogUiState.LoadingUiState).value

    when (uiState) {
        is ListDogUiState.LoadingUiState -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is ListDogUiState.DisplayListDogUiState -> {
                val displayState = uiState
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 180.dp)
                ) {
                    displayState.listDog?.let { listDog ->
                        items(listDog.size) { index ->
                            val dogName = listDog[index]
                            val dogImage = displayState.listDogImages[index]
                            DogItem(name = dogName, imageUrl = dogImage, context = context)
                        }
                    }
                }
        }

            is ListDogUiState.ErrorUiState -> {
                val errorValue = uiState as ListDogUiState.ErrorUiState
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Error occurred!" + errorValue.error.toString())
                }
            }
        }
}
