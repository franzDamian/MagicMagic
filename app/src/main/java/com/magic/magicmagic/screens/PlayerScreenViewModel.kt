package com.magic.magicmagic.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magic.magicmagic.database.Player
import com.magic.magicmagic.database.PlayerDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerScreenViewModel @Inject constructor(
    val playerDao: PlayerDao
): ViewModel() {
    private val _players = playerDao.getAll()
    val players = _players.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addPlayer(name: String) {
        viewModelScope.launch {
            playerDao.insert(Player(name = name))
        }
    }

    fun removePlayer(player: Player) {
        viewModelScope.launch {
            playerDao.delete(player)
        }
    }
}