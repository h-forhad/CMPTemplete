package com.greenrobotdev.favily.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import io.github.xxfast.decompose.router.stack.RoutedContent
import io.github.xxfast.decompose.router.stack.Router
import io.github.xxfast.decompose.router.stack.rememberRouter

@Composable
fun HomeScreen() {
    val router: Router<StoryHomeScreen> = rememberRouter(StoryHomeScreen::class) { listOf(
        StoryHomeScreen.Home
    ) }

    RoutedContent(
        router = router,
    ) { screen ->
        when (screen) {
            StoryHomeScreen.Home -> Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Home")
                Button(onClick = {
                    router.push(StoryHomeScreen.PlanDetails("1"))
                }) {
                    Text("Go to Plan Details")
                }
            }

            is StoryHomeScreen.PlanDetails -> Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text("Details")

                Button(onClick = {
                    router.pop()
                }) {
                    Text("Go to Plan list")
                }
            }

            else -> {

            }
        }
    }
}
