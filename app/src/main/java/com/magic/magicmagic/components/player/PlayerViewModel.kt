package com.magic.magicmagic.components.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magic.magicmagic.storage.StorageResult
import com.magic.magicmagic.storage.entities.Player
import com.magic.magicmagic.storage.usecases.player.AddPlayerUseCase
import com.magic.magicmagic.storage.usecases.player.DeletePlayerUseCase
import com.magic.magicmagic.storage.usecases.player.GetPlayersUseCase
import com.magic.magicmagic.ui.state.PlayerUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val getPlayersUseCase: GetPlayersUseCase,
    private val addPlayerUseCase: AddPlayerUseCase,
    private val deletePlayerUseCase: DeletePlayerUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<PlayerUiState>(PlayerUiState.Loading)
    val uiState: StateFlow<PlayerUiState> = _uiState

    init {
        getPlayers()
    }

    fun addPlayer(player: Player) {
        viewModelScope.launch {
            when (val result = addPlayerUseCase(player)) {
                is StorageResult.Success -> getPlayers()
                is StorageResult.Loading -> PlayerUiState.Loading
                is StorageResult.Error -> _uiState.value =
                    PlayerUiState.Error(result.exception.message ?: "Failed to add quote")
            }
        }
    }

    fun deletePlayer(player: Player) {
        viewModelScope.launch {
            when (val result = deletePlayerUseCase(player)) {
                is StorageResult.Success -> getPlayers()
                is StorageResult.Loading -> PlayerUiState.Loading
                is StorageResult.Error -> _uiState.value =
                    PlayerUiState.Error(result.exception.message ?: "Failed to delete quote")
            }
        }
    }

    private fun getPlayers() {
        viewModelScope.launch {
            getPlayersUseCase().collect { result ->
                _uiState.value = when (result) {
                    is StorageResult.Success -> PlayerUiState.Success(result.data)
                    is StorageResult.Loading -> PlayerUiState.Loading
                    is StorageResult.Error -> PlayerUiState.Error(
                        result.exception.message ?: "Unknown error"
                    )
                }
            }
        }
    }
}