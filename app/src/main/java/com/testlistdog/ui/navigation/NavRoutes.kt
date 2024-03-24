package com.testlistdog.ui.navigation

sealed class NavRoutes(var routes: String){
    object ListDogScreen: NavRoutes("LoginScreen")
}
