package com.greenrobotdev.core.ui

import androidx.compose.runtime.Composable

enum class AppSystemBarStyle {
    AUTO,
    DARK,
    LIGHT;
}

@Composable
expect fun edgeToEdge(
    statusBarStyle: AppSystemBarStyle = AppSystemBarStyle.AUTO,
    navigationBarStyle: AppSystemBarStyle = AppSystemBarStyle.AUTO
)