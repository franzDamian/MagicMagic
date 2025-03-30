package com.magic.magicmagic.storage.usecases.player

import com.magic.magicmagic.storage.StorageResult
import com.magic.magicmagic.storage.entities.Player
import com.magic.magicmagic.storage.repositories.PlayersRepository
import javax.inject.Inject

class AddPlayerUseCase @Inject constructor(private val repository: PlayersRepository) {
    suspend operator fun invoke(player: Player): StorageResult<Unit> = repository.insertPlayer(player)
}