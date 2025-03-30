import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.magic.magicmagic.components.player.AddPlayerDialog
import com.magic.magicmagic.components.player.PlayerListItem
import com.magic.magicmagic.components.player.PlayerViewModel
import com.magic.magicmagic.ui.state.PlayerUiState

@Composable
fun PlayerList(viewModel: PlayerViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    var showAddPlayerDialog by remember { mutableStateOf(false) }

    if (showAddPlayerDialog) {
        AddPlayerDialog(
            onDismiss = { showAddPlayerDialog = false },
            onConfirm = { newPlayer ->
                viewModel.addPlayer(com.magic.magicmagic.storage.entities.Player(name = newPlayer))
                showAddPlayerDialog = false
            })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()) {
            FloatingActionButton(
                onClick = { showAddPlayerDialog = true },
            ) {
                Icon(
                    imageVector = Icons.Filled.Add, contentDescription = "Plus-Icon"
                )
            }
        }

        when (uiState) {
            is PlayerUiState.Success -> {
                val players = (uiState as PlayerUiState.Success).players
                LazyColumn {
                    items(items = players) { player ->
                        PlayerListItem("test")
                    }
                }
            }
            is PlayerUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }

            is PlayerUiState.Error -> {
                Text(
                    text = "Error",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}