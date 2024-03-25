package com.testlistdog.ui.listdogs

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.testlistdog.presentation.listdogs.ListDogViewModel
import com.testlistdog.presentation.listdogs.events.ListDogUiState
import com.testlistdog.ui.listdogs.components.DogError
import com.testlistdog.ui.listdogs.components.DogItem
import com.testlistdog.ui.listdogs.components.DogLoading

@Composable
internal fun ListDogScreen(
    viewModel: ListDogViewModel
) {
    val context = LocalContext.current

    val scrollState = rememberLazyGridState()

    var pageSize by remember { mutableStateOf(10) }

    val uiState = remember(key1 = pageSize) {
        viewModel.getListDogWithImages(pageSize = pageSize)
    }.collectAsState(initial = ListDogUiState.LoadingUiState).value


    when (uiState) {
        is ListDogUiState.LoadingUiState -> {
            DogLoading()
        }

        is ListDogUiState.DisplayListDogUiState -> {
            val displayState = uiState
            LazyVerticalGrid(
                state = scrollState,
                columns = GridCells.Adaptive(minSize = 180.dp)
            ) {
                displayState.listDog?.let { listDog ->
                    items(listDog.size) { index ->
                        val dogName = listDog[index]
                        val dogImage = displayState.listDogImages[index]
                        DogItem(name = dogName, imageUrl = dogImage, context = context)
                    }
                }
                if (scrollState.firstVisibleItemIndex +
                    scrollState.layoutInfo.visibleItemsInfo.size >= pageSize
                ) {
                    pageSize += 10
                }
            }
        }

        is ListDogUiState.ErrorUiState -> {
            DogError {
                viewModel.getListDogWithImages(pageSize)
            }
        }
    }
}
