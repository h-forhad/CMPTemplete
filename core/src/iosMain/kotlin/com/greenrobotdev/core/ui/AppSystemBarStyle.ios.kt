package com.greenrobotdev.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

@Composable
actual fun edgeToEdge(
    statusBarStyle: AppSystemBarStyle,
    navigationBarStyle: AppSystemBarStyle
) {
    DisposableEffect(Unit) {
        onDispose {}
    }
}