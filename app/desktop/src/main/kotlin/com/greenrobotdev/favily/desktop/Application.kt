package com.greenrobotdev.favily.desktop

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import au.com.gridstone.pscore.app.screens.root.RootScreen
import com.greenrobotdev.core.coreModule
import com.greenrobotdev.core.utils.LocalWindowSizeClass
import com.greenrobotdev.onlinestore.di.appStorage
import com.greenrobotdev.onlinestore.onlineStoreModule
import com.greenrobotdev.favily.appModule
import com.greenrobotdev.favily.theme.AppTheme
import io.github.xxfast.decompose.router.LocalRouterContext
import io.github.xxfast.decompose.router.RouterContext
import io.github.xxfast.decompose.router.defaultRouterContext
import org.koin.core.context.startKoin

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun main() {
  application {
    val windowState: WindowState = rememberWindowState(size = DpSize(1420.dp, 1000.dp))
    val rootRouterContext: RouterContext = defaultRouterContext(windowState = windowState)

    startKoin {
      modules(appModule)
      modules(coreModule)
      modules(onlineStoreModule)
    }

    appStorage = System.getProperty("user.home")


    Window(
      title = "WanderWise",
      state = windowState,
      onCloseRequest = { exitApplication() }
    ) {


      val windowSizeClass: WindowSizeClass = calculateWindowSizeClass()

      CompositionLocalProvider(
        LocalRouterContext provides rootRouterContext,
        LocalWindowSizeClass provides windowSizeClass
      ) {
        AppTheme {
          RootScreen()
        }
      }
    }
  }
}
