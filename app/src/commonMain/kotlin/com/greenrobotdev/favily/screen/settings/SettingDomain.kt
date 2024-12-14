package com.greenrobotdev.favily.screen.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.greenrobotdev.favily.data.models.AppSettings
import com.greenrobotdev.favily.data.models.set
import com.greenrobotdev.onlinestore.screen.cart.CartEvent
import com.greenrobotdev.onlinestore.screen.cart.CartState
import com.russhwolf.settings.Settings
import io.github.xxfast.kstore.KStore
import kotlinx.coroutines.flow.Flow

@Composable
fun SettingDomain(
    initialState: SettingsState,
    events: Flow<SettingsEvent>,
    settings: Settings
): SettingsState {

    var appSettings: AppSettings by remember {
        mutableStateOf(initialState.appSettings)
    }

        LaunchedEffect(Unit) {
            events.collect { event ->
                when (event) {
                    is SettingsEvent.UpdateAppTheme -> {
                        appSettings = appSettings.copy(theme = event.theme)
                        settings.set(appSettings)
                    }
                }
            }
        }

        return SettingsState(
            appSettings = appSettings
        )
    }
