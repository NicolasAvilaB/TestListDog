package com.testlistdog.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.testlistdog.presentation.listdogs.ListDogViewModel
import com.testlistdog.presentation.listdogs.events.ListDogUiState

@Composable
internal fun ListDogScreen(
    viewModel: ListDogViewModel
) {
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
        LazyColumn {
            displayState.listDog?.let {
                items(it.size) { index ->
                    DogItem(name = displayState.listDog.toString(), image = displayState.listDogImages)
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
                Text(text = "Error occurred!"+errorValue.error.toString())
            }
	    }
    }
}


@Composable
fun DogItem(name: String, image: String) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = name)

	Box {    
	    Image(
            painter = rememberAsyncImagePainter(image),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 8.dp)
                .size(120.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(Color.LightGray),
            contentScale = ContentScale.Crop
            )
        }
    }
}
