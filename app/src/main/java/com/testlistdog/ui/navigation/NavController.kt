package com.testlistdog.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import com.testlistdog.ui.di.ListDogProvider

@Composable
fun NavController(fragmentActivity: FragmentActivity) {
    val startDestination: String = NavRoutes.ListDogScreen.routes
    val navController = rememberNavController()

    val listDogProvider = ListDogProvider()
    val listDogViewModel = listDogProvider.getViewModel(fragmentActivity)

    NavHost(
        navController = navController,
        startDestination = startDestination
    )
    {
        listDog(listDogViewModel)
    }
}