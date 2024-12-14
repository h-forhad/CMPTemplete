package com.greenrobotdev.core.screen

import app.cash.molecule.AndroidUiDispatcher
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

actual fun ViewModel.ViewModelDispatcher(): CoroutineContext = AndroidUiDispatcher.Main + SupervisorJob()