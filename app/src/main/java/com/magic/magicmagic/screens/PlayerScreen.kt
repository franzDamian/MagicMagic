package com.magic.magicmagic.screens

import PlayerList
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.magic.magicmagic.navigation.HomeRoute
import com.magic.magicmagic.navigation.LocalNavController
import com.magic.magicmagic.navigation.navigateSingleTopTo

@Composable
fun PlayerScreen() {
    val navController = LocalNavController.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            navController.navigateSingleTopTo(HomeRoute.route)
        }) {
            Text("Go to home")
        }
        PlayerList()
    }
}