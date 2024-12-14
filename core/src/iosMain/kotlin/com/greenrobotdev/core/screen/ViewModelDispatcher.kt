package com.greenrobotdev.core.screen

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

actual fun ViewModel.ViewModelDispatcher(): CoroutineContext = Dispatchers.Main