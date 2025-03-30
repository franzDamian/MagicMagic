import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.magic.magicmagic.components.player.Player
import com.magic.magicmagic.ui.theme.MagicMagicTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import kotlin.concurrent.thread

class PlayerListViewModel : ViewModel() {
    private val _players = MutableStateFlow(listOf(Player("Franz"), Player("Theo")))
    val players = _players.asStateFlow()

    fun addPlayer(playerName: String) {
        _players.value += Player(playerName)
    }

    fun updatePlayerName(index: Int, newName: String) {
        val updatedPlayers = _players.value.toMutableList()
        updatedPlayers[index] = Player(newName)
        _players.value = updatedPlayers
    }
}

@Composable
fun PlayerList() {
    val viewModel = PlayerListViewModel()
    val players by viewModel.players.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()) {
            FloatingActionButton(
                onClick = { viewModel.addPlayer("Neuer Spieler") },
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Plus-Icon"
                )
            }
        }

        LazyColumn {
            items(players.size) { index ->
                var playerName by remember { mutableStateOf(players[index].name) }
                TextField(
                    value = playerName,
                    onValueChange = {
                        playerName = it
                        viewModel.updatePlayerName(index, it)
                    },
                    label = { Text("Spielername") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )
            }
        }
    }
}