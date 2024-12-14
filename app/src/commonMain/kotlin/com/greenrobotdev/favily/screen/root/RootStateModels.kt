package com.greenrobotdev.favily.screen.root

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.greenrobotdev.favily.screen.root.RootScreens.Settings
import com.greenrobotdev.favily.screen.root.RootScreens.Home
import composemultiplatformtemplate.app.generated.resources.Res
import composemultiplatformtemplate.app.generated.resources.home_tab
import composemultiplatformtemplate.app.generated.resources.settings_tab
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource

enum class RootScreens {
  Home,
  Settings,
}

internal val RootScreens.text: String
  @Composable get() = when (this) {
    Home -> stringResource( Res.string.home_tab)
    Settings -> stringResource( Res.string.settings_tab)
  }

internal val RootScreens.icon: ImageVector
  get() = when (this) {
    Home -> Icons.Rounded.Home
    Settings -> Icons.Rounded.Settings
  }
