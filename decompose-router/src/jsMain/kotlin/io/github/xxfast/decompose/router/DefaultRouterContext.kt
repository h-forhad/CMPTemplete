package io.github.xxfast.decompose.router

import com.arkivanov.essenty.backhandler.BackDispatcher
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.essenty.lifecycle.stop
import kotlinx.browser.document
import web.dom.DocumentVisibilityState
import web.dom.document as webDocument

fun defaultRouterContext(): RouterContext {
  val backDispatcher = BackDispatcher()
  val lifecycle = LifecycleRegistry()
  lifecycle.attachToDocument()
  return RouterContext(lifecycle = lifecycle, backHandler = backDispatcher)
}

// Attaches the LifecycleRegistry to the document
private fun LifecycleRegistry.attachToDocument() {
  fun onVisibilityChanged() =
    if (webDocument.visibilityState == DocumentVisibilityState.visible) resume()
    else stop()

  onVisibilityChanged()

  document.addEventListener("visibilitychange", callback = { onVisibilityChanged() })
}

