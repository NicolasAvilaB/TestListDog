package com.testlistdog.ui.listdogs.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.testlistdog.R

@Composable
internal fun DogError(onClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(id = R.string.error_ocurred))
        Button(onClick = { onClick.invoke() }) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}