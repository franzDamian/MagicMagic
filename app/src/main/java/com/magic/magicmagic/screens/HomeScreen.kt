package com.magic.magicmagic.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.magic.magicmagic.navigation.LocalNavController
import com.magic.magicmagic.navigation.PlayerRoute
import com.magic.magicmagic.navigation.navigateSingleTopTo

@Composable
fun HomeScreen() {
    val navController = LocalNavController.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            navController.navigateSingleTopTo(PlayerRoute.route)
        }) {
            Text("Go to player")
        }
    }
}