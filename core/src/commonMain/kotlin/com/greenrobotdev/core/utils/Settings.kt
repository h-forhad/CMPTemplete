package com.greenrobotdev.core.utils
import com.russhwolf.settings.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.getBooleanFlow
import com.russhwolf.settings.coroutines.getStringFlow
import com.russhwolf.settings.coroutines.getStringOrNullFlow
import com.russhwolf.settings.get
import kotlinx.coroutines.flow.map
import kotlin.time.Duration

val Settings: Settings = Settings()
val observableSettings: ObservableSettings = Settings() as ObservableSettings

@OptIn(ExperimentalSettingsApi::class)
@Composable
inline fun <reified T : Enum<T>> ObservableSettings.collectEnumAsState(
    key: String,
    default: T,
    encoder: (T) -> String = { it.name },
    crossinline decoder: (String) -> T = { enumValueOf(it) },
): State<T> = getStringFlow(key, encoder(default))
    .map { decoder(it) }
    .collectAsState(default)
