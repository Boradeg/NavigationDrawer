package com.example.navdrawertask.presentation.navigation

sealed class Screens(val route : String){
    object HomeScreen : Screens("home_screen")
}