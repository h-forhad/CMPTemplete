package com.greenrobotdev.favily.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.greenrobotdev.core.ui.DarkColors
import com.greenrobotdev.core.ui.LightColors
import com.greenrobotdev.core.ui.shapes
import com.greenrobotdev.core.ui.typography
import com.greenrobotdev.core.utils.Settings
import com.greenrobotdev.core.utils.collectEnumAsState
import com.greenrobotdev.core.utils.observableSettings
import com.greenrobotdev.favily.data.models.APP_SETTINGS_APP_THEME
import com.greenrobotdev.favily.data.models.AppSettings
import com.greenrobotdev.favily.data.models.AppTheme
import com.greenrobotdev.favily.data.models.get

@Composable
actual fun AppTheme(
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val appSettings: AppSettings = remember { Settings.get() }

    val theme: AppTheme by observableSettings
        .collectEnumAsState(APP_SETTINGS_APP_THEME, appSettings.theme)

    val darkTheme: Boolean = when (theme) {
        AppTheme.Dark -> true
        AppTheme.Light -> false
        else -> isSystemInDarkTheme()
    }

    val colors = when {
        (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) -> {
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColors
        else -> LightColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content,
        shapes = shapes,
        typography = typography
    )
}
