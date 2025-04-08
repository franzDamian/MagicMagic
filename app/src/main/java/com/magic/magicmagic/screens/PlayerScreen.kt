package com.magic.magicmagic.screens

import PlayerList
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.magic.magicmagic.navigation.HomeRoute
import com.magic.magicmagic.navigation.LocalNavController
import com.magic.magicmagic.navigation.navigateSingleTopTo

@Composable
fun PlayerScreen(
    viewModel: PlayerScreenViewModel = hiltViewModel(),
) {
    val players = viewModel.players.collectAsStateWithLifecycle()

    var dialogOpen by remember { mutableStateOf(false) }

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
        FloatingActionButton(
            onClick = { dialogOpen = true },
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Plus-Icon"
            )
        }
        if (dialogOpen) {
            AddPlayerDialog(
                onAdd = viewModel::addPlayer,
                onDismiss = { dialogOpen = false }
            )
        }
        PlayerList(players.value, viewModel::removePlayer)
    }
}

@Composable
fun AddPlayerDialog(
    onAdd: (name: String) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            var name by remember { mutableStateOf("") }

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Spieler hinzufügen", style = MaterialTheme.typography.titleMedium)

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = onDismiss,
                    ) {
                        Text("Abbrechen")
                    }
                    Button(
                        onClick = {
                            onAdd(name)
                            onDismiss()
                        },
                    ) {
                        Text("Hinzufügen")
                    }
                }
            }
        }
    }
}