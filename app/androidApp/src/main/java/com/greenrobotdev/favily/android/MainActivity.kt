package com.greenrobotdev.favily.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import au.com.gridstone.pscore.app.screens.root.RootScreen
import com.greenrobotdev.favily.theme.AppTheme
import com.greenrobotdev.core.utils.LocalWindowSizeClass
import com.greenrobotdev.onlinestore.di.appStorage
import io.github.xxfast.decompose.router.LocalRouterContext
import io.github.xxfast.decompose.router.RouterContext
import io.github.xxfast.decompose.router.defaultRouterContext

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appStorage = filesDir.path

        WindowCompat.setDecorFitsSystemWindows(window, false)
        val rootComponentContext: RouterContext = defaultRouterContext()

        setContent {
            val windowSizeClass: WindowSizeClass = calculateWindowSizeClass()

            CompositionLocalProvider(
                LocalRouterContext provides rootComponentContext,
                LocalWindowSizeClass provides windowSizeClass,
            ) {
                AppTheme {
                    Surface(tonalElevation = 5.dp) {
                       RootScreen()
                    }
                }

            }
        }
    }
}
