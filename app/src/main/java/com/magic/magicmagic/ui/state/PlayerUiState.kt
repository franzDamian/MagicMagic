package com.magic.magicmagic.ui.state

import com.magic.magicmagic.storage.entities.Player

sealed class PlayerUiState {
    data class Success(val players: List<Player>) : PlayerUiState()
    data object Loading : PlayerUiState()
    data class Error(val message: String) : PlayerUiState()
}