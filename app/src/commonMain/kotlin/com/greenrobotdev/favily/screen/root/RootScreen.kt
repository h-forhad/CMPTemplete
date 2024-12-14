package au.com.gridstone.pscore.app.screens.root

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass.Companion.Expanded
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.greenrobotdev.core.utils.LocalWindowSizeClass
import com.greenrobotdev.favily.screen.root.RootScreens
import com.greenrobotdev.favily.screen.root.icon
import com.greenrobotdev.favily.screen.root.text
import com.greenrobotdev.onlinestore.screen.home.HomeScreen
import composemultiplatformtemplate.app.generated.resources.Res
import composemultiplatformtemplate.app.generated.resources.ic_test
import io.github.xxfast.decompose.router.stack.RoutedContent
import io.github.xxfast.decompose.router.stack.Router
import kotlinx.coroutines.CoroutineScope
import org.jetbrains.compose.resources.imageResource
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import com.arkivanov.decompose.router.stack.pushToFront
import com.greenrobotdev.favily.screen.settings.SettingsScreen
import composemultiplatformtemplate.app.generated.resources.ic_app
import composemultiplatformtemplate.app.generated.resources.ic_launcher
import io.github.xxfast.decompose.router.stack.rememberRouter
import org.jetbrains.compose.resources.painterResource

@Composable
fun RootScreen() {

    val windowSizeClass: WindowSizeClass = LocalWindowSizeClass.current

    val router: Router<RootScreens> = rememberRouter(
        type = RootScreens::class,
        handleBackButton = false
    ) { RootScreens.entries.sortedBy { it == RootScreens.Home } }

    Row {
        if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded) NavigationRail(
            header = {
                Icon(
                    painter = painterResource(Res.drawable.ic_test),
                    contentDescription = "app",
                    tint = MaterialTheme.colorScheme.surfaceTint,
                    modifier = Modifier
                        .size(40.dp)
                )
            },
            modifier = Modifier.padding(vertical = 12.dp)
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            RootScreens.entries.forEach { screen ->
                NavigationRailItem(
                    icon = { Icon(screen.icon, contentDescription = null) },
                    label = {
                        Text(
                            text = screen.text,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    selected = screen == router.stack.value.active.configuration,
                    onClick = { router.pushToFront(screen) }
                )
            }
        }

        Scaffold(
            contentWindowInsets = WindowInsets.ime,
            bottomBar = {
                val isImeVisible: Boolean = WindowInsets.ime.getBottom(LocalDensity.current) > 0
                val isKeyboardOpen: Boolean by rememberUpdatedState(isImeVisible)

                AnimatedVisibility(
                    visible = windowSizeClass.widthSizeClass != WindowWidthSizeClass.Expanded && !isKeyboardOpen,
                    enter = expandVertically { offset -> offset },
                    exit = shrinkVertically { offset -> offset },
                    modifier = Modifier
                        .animateContentSize()
                        .windowInsetsPadding(WindowInsets.ime),
                ) {
                    BottomAppBar {
                        RootScreens.entries.forEach { screen ->
                            NavigationBarItem(
                                icon = { Icon(screen.icon, contentDescription = null) },
                                label = {
                                    Text(
                                        text = screen.text,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                },
                                selected = screen == router.stack.value.active.configuration,
                                onClick = { router.pushToFront(screen) }
                            )
                        }
                    }
                }
            },
        ) { paddingValues ->
            RoutedContent(
                router = router,
                animation = stackAnimation(fade()),
                modifier = Modifier
                    .consumeWindowInsets(WindowInsets.navigationBars)
                    .padding(bottom = paddingValues.calculateBottomPadding())
            ) { screen ->
                when (screen) {
                    RootScreens.Home -> HomeScreen()
                    RootScreens.Settings -> SettingsScreen()
                }
            }
        }
    }
}
