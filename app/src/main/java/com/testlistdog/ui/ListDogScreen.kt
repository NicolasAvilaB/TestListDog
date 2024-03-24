package com.testlistdog.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.testlistdog.presentation.listdogs.ListDogViewModel
import com.testlistdog.presentation.listdogs.events.ListDogUiState

@Composable
internal fun ListDogScreen(
    viewModel: ListDogViewModel
) {
    val context = LocalContext.current

    val uiState by viewModel.getListDogWithImages()
        .collectAsState(initial = ListDogUiState.LoadingUiState)

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
                val displayState = uiState as ListDogUiState.DisplayListDogUiState
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


@Composable
fun DogItem(name: String, imageUrl: String, context: Context) {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {

        Box (
            Modifier.clickable {
                Toast.makeText(context, "La raza seleccionada es $name", Toast.LENGTH_SHORT).show()
            }
        ){
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .size(166.dp)
                    .clip(shape = RoundedCornerShape(11.dp))
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop
            )
        }
    }
}
