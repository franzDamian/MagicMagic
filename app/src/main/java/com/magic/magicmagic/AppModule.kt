package com.magic.magicmagic

import android.content.Context
import androidx.room.Room
import com.magic.magicmagic.database.AppDatabase
import com.magic.magicmagic.database.PlayerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDb(@ApplicationContext ctx: Context): AppDatabase {
        return Room.databaseBuilder(
            ctx,
            AppDatabase::class.java,
            "magicmagic-db"
        ).build()
    }

    @Provides
    fun providePlayerDao(db: AppDatabase): PlayerDao = db.playerDao()
}