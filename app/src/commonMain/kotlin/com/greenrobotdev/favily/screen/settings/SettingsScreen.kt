package com.greenrobotdev.favily.screen.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Preview
import androidx.compose.material.icons.filled.ShoppingCartCheckout
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.dismiss
import com.greenrobotdev.core.ui.AppSystemBarStyle
import com.greenrobotdev.core.ui.edgeToEdge
import com.greenrobotdev.core.utils.navigationBarPadding
import com.greenrobotdev.favily.data.models.AppTheme
import com.greenrobotdev.onlinestore.screen.cart.CartProceedView
import com.greenrobotdev.onlinestore.screen.cart.CartProductList
import com.greenrobotdev.onlinestore.screen.cart.nothing
import com.greenrobotdev.onlinestore.ui.EmptyView
import io.github.xxfast.decompose.router.rememberOnRoute
import io.github.xxfast.decompose.router.slot.RoutedContent
import io.github.xxfast.decompose.router.slot.Router
import io.github.xxfast.decompose.router.slot.rememberRouter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {

    val viewModel: SettingsViewModel = rememberOnRoute(SettingsViewModel::class) { savedState ->
        SettingsViewModel(savedState)
    }

    val state: SettingsState by viewModel.states.collectAsState()

    SettingsView(
        state = state,
        onUpdateTheme = viewModel::onUpdateTheme,
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsView(
    state: SettingsState,
    onUpdateTheme: (AppTheme) -> Unit,
){

    edgeToEdge(
        statusBarStyle = AppSystemBarStyle.LIGHT, navigationBarStyle = AppSystemBarStyle.DARK
    )

    val router = rememberRouter(SettingsScreens::class) { null }

    RoutedContent(router) { content ->

        BasicAlertDialog(
            modifier = Modifier.wrapContentSize(),
            onDismissRequest = { router.dismiss() },
        ) {

            AnimatedVisibility(
                visible = true,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colorScheme.surface),
                ) {

                    when (content) {
                        SettingsScreens.AppTheme -> AppThemeSettingsView(
                            appTheme = state.appSettings.theme,
                            onUpdateTheme = onUpdateTheme
                        )

                        else -> {

                        }
                    }
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier,
        topBar = {
            LargeTopAppBar(
                title = { Text(text = "Settings") },
            )
        }
    ) { scaffoldPadding ->
        LazyColumn(
            modifier = Modifier.scrollable(rememberScrollState(), Orientation.Vertical)
                .fillMaxSize().padding(scaffoldPadding)
        ) {
            item {
                SettingsItemView(title = "Theme",
                    subtitle = state.appSettings.theme.text,
                    iconVector = Icons.Default.Preview,
                    onClick = { router.activate(SettingsScreens.AppTheme) })
            }
        }
    }
}


@Composable
fun AppThemeSettingsView(
    appTheme: AppTheme,
    onUpdateTheme: (AppTheme) -> Unit
){
    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Select Theme",
            style = MaterialTheme.typography.titleLarge,
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AppTheme.entries.forEach { theme ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onUpdateTheme(theme) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = appTheme == theme,
                        onClick = { onUpdateTheme(theme) }
                    )

                    Text(
                        text = theme.text,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
    }
}

@Composable
private fun SettingsItemView(
    title: String,
    subtitle: String? = null,
    iconVector: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            imageVector = iconVector,
            contentDescription = null,
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )

            if (subtitle != null) Text(
                text = subtitle,
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}
