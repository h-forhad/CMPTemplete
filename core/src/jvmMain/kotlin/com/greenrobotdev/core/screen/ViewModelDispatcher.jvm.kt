package com.greenrobotdev.core.screen

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.jetbrains.skiko.MainUIDispatcher
import kotlin.coroutines.CoroutineContext

actual fun ViewModel.ViewModelDispatcher(): CoroutineContext = Dispatchers.Unconfined + SupervisorJob()