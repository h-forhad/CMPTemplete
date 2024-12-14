package com.greenrobotdev.favily.screen.settings

import androidx.compose.runtime.Composable
import com.greenrobotdev.favily.data.models.AppSettings
import com.greenrobotdev.favily.data.models.AppTheme
import composemultiplatformtemplate.app.generated.resources.Res
import composemultiplatformtemplate.app.generated.resources.app_theme_dark
import composemultiplatformtemplate.app.generated.resources.app_theme_light
import composemultiplatformtemplate.app.generated.resources.app_theme_system_default
import org.jetbrains.compose.resources.stringResource
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import kotlinx.serialization.Serializable

private const val APP_SETTINGS_APP_THEME = "app_settings_theme"

@Serializable
data class SettingsState(
    val appSettings: AppSettings
)

sealed class SettingsEvent {
    data class UpdateAppTheme(val theme: AppTheme) : SettingsEvent()
}

sealed class SettingsScreens {
    data object AppTheme : SettingsScreens()
    data object Settings : SettingsScreens()
}