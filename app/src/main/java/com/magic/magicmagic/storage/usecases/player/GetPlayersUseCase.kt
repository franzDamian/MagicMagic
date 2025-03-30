package com.magic.magicmagic.storage.usecases.player

import com.magic.magicmagic.storage.StorageResult
import com.magic.magicmagic.storage.entities.Player
import com.magic.magicmagic.storage.repositories.PlayersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlayersUseCase @Inject constructor(private val repository: PlayersRepository) {
    suspend operator fun invoke(): Flow<StorageResult<List<Player>>> = repository.getAllPlayers()
}