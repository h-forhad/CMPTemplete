package com.greenrobotdev.favily

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import au.com.gridstone.pscore.app.screens.root.RootScreen
import com.greenrobotdev.core.coreModule
import com.greenrobotdev.core.utils.LocalWindowSizeClass
import com.greenrobotdev.core.utils.Settings
import com.greenrobotdev.core.utils.collectEnumAsState
import com.greenrobotdev.core.utils.observableSettings
import com.greenrobotdev.favily.data.models.APP_SETTINGS_APP_THEME
import com.greenrobotdev.favily.data.models.AppSettings
import com.greenrobotdev.favily.data.models.AppTheme
import com.greenrobotdev.favily.data.models.get
import com.greenrobotdev.favily.theme.AppTheme
import com.greenrobotdev.onlinestore.di.appStorage
import com.greenrobotdev.onlinestore.onlineStoreModule
import com.russhwolf.settings.Settings
import io.github.xxfast.decompose.router.LocalRouterContext
import io.github.xxfast.decompose.router.RouterContext
import org.koin.core.context.startKoin
import platform.Foundation.NSHomeDirectory
import platform.UIKit.UIColor
import platform.UIKit.UITraitCollection
import platform.UIKit.UIUserInterfaceStyle
import platform.UIKit.currentTraitCollection

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Suppress("unused") // used by swift code
fun createViewController(
    routerContext: RouterContext,
) = ComposeUIViewController {
    appStorage = NSHomeDirectory()

    startKoin {
        modules(appModule)
        modules(coreModule)
        modules(onlineStoreModule)
    }

    val windowSizeClass: WindowSizeClass = calculateWindowSizeClass()

    BoxWithConstraints{
        CompositionLocalProvider(
            LocalRouterContext provides routerContext,
            LocalWindowSizeClass provides windowSizeClass
        ) {
            AppTheme {
                Surface(tonalElevation = 5.dp) {
                   RootScreen()
                }
            }
        }
    }
}

fun getStatusBarColor() : UIColor {

    val md_theme_light_primary = UIColor(red= 0x00/255.0, green= 0x66/255.0, blue= 0x86/255.0, alpha= 1.0)

    val md_theme_dark_primary = UIColor(red= 0x70/255.0, green= 0xD2/255.0, blue= 0xFF/255.0, alpha= 1.0)

    val appTheme: AppSettings = Settings().get()

    return when (appTheme.theme) {
        com.greenrobotdev.favily.data.models.AppTheme.Light -> md_theme_light_primary
        com.greenrobotdev.favily.data.models.AppTheme.Dark -> md_theme_dark_primary
        else -> {
            val userInterfaceStyle = UITraitCollection.currentTraitCollection.userInterfaceStyle
            when (userInterfaceStyle) {
                UIUserInterfaceStyle.UIUserInterfaceStyleLight -> md_theme_light_primary
                UIUserInterfaceStyle.UIUserInterfaceStyleDark -> md_theme_dark_primary
                else -> md_theme_light_primary
            }
        }
    }

}
