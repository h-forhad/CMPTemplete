package com.greenrobotdev.favily.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.greenrobotdev.core.ui.DarkColors
import com.greenrobotdev.core.ui.LightColors
import com.greenrobotdev.core.ui.typography
import com.greenrobotdev.core.utils.Settings
import com.greenrobotdev.core.utils.collectEnumAsState
import com.greenrobotdev.core.utils.observableSettings
import com.greenrobotdev.favily.data.models.APP_SETTINGS_APP_THEME
import com.greenrobotdev.favily.data.models.AppSettings
import com.greenrobotdev.favily.data.models.AppTheme
import com.greenrobotdev.favily.data.models.AppTheme.Dark
import com.greenrobotdev.favily.data.models.AppTheme.Light
import com.greenrobotdev.favily.data.models.AppTheme.SystemDefault
import com.greenrobotdev.favily.data.models.get

@Composable
actual fun AppTheme(
    content: @Composable () -> Unit,
) {
    val appSettings: AppSettings = remember { Settings.get() }

    val theme: AppTheme by observableSettings
        .collectEnumAsState(APP_SETTINGS_APP_THEME, appSettings.theme)

    val colorScheme: ColorScheme = when (theme) {
        Dark -> DarkColors
        Light -> LightColors
        SystemDefault -> if (isSystemInDarkTheme()) DarkColors else LightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
        typography = typography,
    )
}
