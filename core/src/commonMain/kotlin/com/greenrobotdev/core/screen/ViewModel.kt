package com.greenrobotdev.core.screen

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import org.koin.core.component.KoinComponent
import kotlin.coroutines.CoroutineContext

abstract class ViewModel: InstanceKeeper.Instance, CoroutineScope, KoinComponent {
  override val coroutineContext: CoroutineContext = ViewModelDispatcher()
  override fun onDestroy() { coroutineContext.cancel() }
}
