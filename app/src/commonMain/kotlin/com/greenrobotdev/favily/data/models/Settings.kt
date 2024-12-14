package com.greenrobotdev.favily.data.models

import androidx.compose.runtime.Composable
import composemultiplatformtemplate.app.generated.resources.Res
import composemultiplatformtemplate.app.generated.resources.app_theme_dark
import composemultiplatformtemplate.app.generated.resources.app_theme_light
import composemultiplatformtemplate.app.generated.resources.app_theme_system_default
import org.jetbrains.compose.resources.stringResource
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import kotlinx.serialization.Serializable

const val APP_SETTINGS_APP_THEME = "app_settings_theme"

val LocalAppSettingsProvider = androidx.compose.runtime.staticCompositionLocalOf<AppSettings> {
    error("No AppTheme provided")
}

@Serializable
data class AppSettings(
    val theme: AppTheme = AppTheme.SystemDefault
)

enum class AppTheme {
    Light,
    Dark,
    SystemDefault;

    val text: String
        @Composable get() = when (this) {
            Light -> stringResource(Res.string.app_theme_light)
            Dark -> stringResource(Res.string.app_theme_dark)
            SystemDefault -> stringResource(Res.string.app_theme_system_default)
        }
}

fun Settings.get(): AppSettings = AppSettings(
    theme = this.get<String>(APP_SETTINGS_APP_THEME)
        ?.let { AppTheme.valueOf(it) }
        ?: AppTheme.SystemDefault
)

fun Settings.set(settings: AppSettings) = apply {
    this.putString(APP_SETTINGS_APP_THEME, settings.theme.name)
}