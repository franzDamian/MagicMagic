import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.magic.magicmagic.database.Player

@Composable
fun PlayerList(
    players: List<Player>,
    onRemovePlayer: (player: Player) -> Unit,
) {

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(players) { player ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = player.name,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Spielername") },
                )
                IconButton(onClick = { onRemovePlayer(player) }) {
                    Icon(Icons.Filled.Delete, contentDescription = "Spieler entfernen")
                }
            }
        }
    }
}