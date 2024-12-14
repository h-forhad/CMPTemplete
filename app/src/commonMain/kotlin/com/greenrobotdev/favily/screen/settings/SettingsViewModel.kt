package com.greenrobotdev.favily.screen.settings

import app.cash.molecule.RecompositionMode
import app.cash.molecule.moleculeFlow
import com.greenrobotdev.core.screen.ViewModel
import com.greenrobotdev.core.utils.Settings
import com.greenrobotdev.favily.data.models.AppSettings
import com.greenrobotdev.favily.data.models.AppTheme
import com.greenrobotdev.favily.data.models.get
import com.greenrobotdev.onlinestore.data.cartStore
import com.greenrobotdev.onlinestore.domain.entity.CartItem
import com.russhwolf.settings.Settings
import io.github.xxfast.decompose.router.RouterContext
import io.github.xxfast.decompose.router.state
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class SettingsViewModel(
    context: RouterContext,
) : ViewModel(), KoinComponent {

    private val appSettings: AppSettings = Settings.get()

    private val initialState: SettingsState = context.state(
        SettingsState(
            appSettings = appSettings
        )
    ) { states.value }

    private val eventsFlow: MutableSharedFlow<SettingsEvent> = MutableSharedFlow(5)

    val states by lazy {
        moleculeFlow(RecompositionMode.Immediate) {
            SettingDomain(initialState, eventsFlow, Settings)
        }.stateIn(this, SharingStarted.Lazily, initialState)
    }

    fun onUpdateTheme(theme: AppTheme) {
        launch {
            eventsFlow.emit(SettingsEvent.UpdateAppTheme(theme))
        }
    }

}
