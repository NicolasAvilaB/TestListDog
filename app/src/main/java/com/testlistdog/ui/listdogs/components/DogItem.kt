package com.testlistdog.ui.listdogs.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

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