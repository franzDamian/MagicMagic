package com.magic.magicmagic.storage

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.magic.magicmagic.storage.entities.Player
import com.magic.magicmagic.storage.entities.PlayerDao

@Database(entities = [Player::class], version = 1, exportSchema = false)
abstract class MagicMagicDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao
}
