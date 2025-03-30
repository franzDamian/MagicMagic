package com.magic.magicmagic.storage.repositories

import com.magic.magicmagic.storage.StorageResult
import com.magic.magicmagic.storage.entities.Player
import com.magic.magicmagic.storage.entities.PlayerDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlayersRepository @Inject constructor(private val playerDao: PlayerDao) {
    suspend fun getAllPlayers(): Flow<StorageResult<List<Player>>> = flow {
        emit(StorageResult.Loading)
        try {
            playerDao.getAllPlayers().collect { players ->
                emit(StorageResult.Success(players))
            }
        } catch (e: Exception) {
            emit(StorageResult.Error(e))
        }
    }

    suspend fun insertPlayer(player: Player): StorageResult<Unit> {
        return try {
            playerDao.insertPlayer(player)
            StorageResult.Success(Unit)
        } catch (e: Exception) {
            StorageResult.Error(e)
        }
    }

    suspend fun deletePlayer(player: Player): StorageResult<Unit> {
        return try {
            playerDao.deletePlayer(player)
            StorageResult.Success(Unit)
        } catch (e: Exception) {
            StorageResult.Error(e)
        }
    }
}