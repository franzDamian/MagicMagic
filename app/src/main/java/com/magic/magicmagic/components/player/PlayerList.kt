package com.magic.magicmagic.components.player

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PlayerListViewModel : ViewModel() {
    private val _players = MutableStateFlow(listOf(Player("Franz"), Player("Theo")))
    val players = _players.asStateFlow()

    fun addPlayer(playerName: String) {
        _players.value += Player(playerName)
    }
}

@Composable
fun PlayerList() {
    val viewModel = PlayerListViewModel()
    val players by viewModel.players.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn {
            items(players) { player ->
                PlayerListItem(name = player.name)
            }
        }

        Button(onClick = { viewModel.addPlayer("blabla")}) {
            Text("Test")
        }
    }
}
