package com.smtersoyoglu.navigationcodelab

sealed class Screen (val route: String) {

    object Home : Screen("home")
    object Second : Screen("second")
    object Last : Screen("last")


}