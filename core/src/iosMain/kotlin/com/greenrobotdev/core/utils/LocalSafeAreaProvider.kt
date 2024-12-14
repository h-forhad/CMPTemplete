package com.greenrobotdev.core.utils

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

data class SafeArea(
    val top: Float = 0f,
    val bottom: Float = 0f,
    val left: Float = 0f,
    val right: Float = 0f,

)

val LocalSafeAreaProvider : ProvidableCompositionLocal<SafeArea> =
    staticCompositionLocalOf { SafeArea() }