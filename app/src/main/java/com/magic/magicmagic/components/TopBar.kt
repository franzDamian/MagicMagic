package com.magic.magicmagic.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import com.magic.magicmagic.navigation.HomeRoute
import com.magic.magicmagic.navigation.LocalNavController
import com.magic.magicmagic.navigation.routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    val navController = LocalNavController.current

    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen = routes.find { it.route == currentDestination?.route } ?: HomeRoute

    TopAppBar(
        title = {
            Text(currentScreen.title)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}