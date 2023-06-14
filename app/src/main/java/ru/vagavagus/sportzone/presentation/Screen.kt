package ru.vagavagus.sportzone.presentation

sealed class Screen(val route: String) {
    object Splash: Screen(route = "splash_screen")
    object Home: Screen(route = "splash_home")
    object Details: Screen(route = "splash_details")
    object WebView: Screen(route = "webview_screen")
}