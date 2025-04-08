package com.magic.magicmagic.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {
    @Insert
    suspend fun insert(player: Player)

    @Delete
    suspend fun delete(player: Player)

    @Query("SELECT * FROM Player")
    fun getAll(): Flow<List<Player>>
}