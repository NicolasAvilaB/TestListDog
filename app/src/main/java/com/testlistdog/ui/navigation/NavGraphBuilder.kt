package com.testlistdog.ui.navigation

import com.testlistdog.presentation.listdogs.ListDogViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.testlistdog.ui.ListDogScreen

internal fun NavGraphBuilder.listDog(
    viewModel: ListDogViewModel
) = composable(
    route = NavRoutes.ListDogScreen.routes
) {
    ListDogScreen(
    	viewModel = viewModel
    )
}