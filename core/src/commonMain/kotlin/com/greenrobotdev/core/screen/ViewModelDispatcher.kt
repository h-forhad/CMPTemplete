package com.greenrobotdev.core.screen

import kotlin.coroutines.CoroutineContext

expect fun ViewModel.ViewModelDispatcher(): CoroutineContext
