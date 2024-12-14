package com.greenrobotdev.favily

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.greenrobotdev.favily.screen.home.HomeScreen

fun main() {
    application {
        Window(title = "KMP Demo", onCloseRequest = ::exitApplication) {
            HomeScreen()
        }
    }
}