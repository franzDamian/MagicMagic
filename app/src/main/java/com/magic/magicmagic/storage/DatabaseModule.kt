package com.magic.magicmagic.storage

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideMagicMagicDatabase(@ApplicationContext context: Context): MagicMagicDatabase {
        return Room.databaseBuilder(
            context,
            MagicMagicDatabase::class.java,
            name = "magicMagicDatabase"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}