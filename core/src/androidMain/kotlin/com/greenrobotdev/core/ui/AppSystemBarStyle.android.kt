package com.greenrobotdev.core.ui
import android.content.Context
import android.graphics.Color
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun edgeToEdge(
    statusBarStyle: AppSystemBarStyle,
    navigationBarStyle: AppSystemBarStyle
) {
    val context: Context = LocalContext.current
    if (context !is ComponentActivity) return

    DisposableEffect(Unit) {
        val statusBarSystemStyle: SystemBarStyle = when (statusBarStyle) {
            AppSystemBarStyle.AUTO -> SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT)
            AppSystemBarStyle.LIGHT -> SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
            AppSystemBarStyle.DARK -> SystemBarStyle.dark(Color.TRANSPARENT)
        }

        val navigationBarSystemStyle: SystemBarStyle = when (navigationBarStyle) {
            AppSystemBarStyle.AUTO -> SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT)
            AppSystemBarStyle.LIGHT -> SystemBarStyle.dark(Color.TRANSPARENT)
            AppSystemBarStyle.DARK -> SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        }

        context.enableEdgeToEdge(
            statusBarStyle = statusBarSystemStyle,
            navigationBarStyle = navigationBarSystemStyle
        )

        onDispose {}
    }
}