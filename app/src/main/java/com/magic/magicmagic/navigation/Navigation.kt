package com.magic.magicmagic.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.magic.magicmagic.components.TopBar
import com.magic.magicmagic.screens.HomeScreen
import com.magic.magicmagic.screens.PlayerScreen

@Composable
fun NavigationProvider(modifier: Modifier) {
    val navController = rememberNavController()

    CompositionLocalProvider(LocalNavController provides navController) {
        Scaffold(
            modifier = modifier,
            topBar = { TopBar() }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = HomeRoute.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(route = HomeRoute.route) {
                    HomeScreen()
                }
                composable(route = PlayerRoute.route) {
                    PlayerScreen()
                }
            }
        }
    }
}