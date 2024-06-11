package com.example.myquiz

import android.content.Context
import androidx.room.Room
import com.example.myquiz.model.LeaderBordRepository
import com.example.myquiz.model.PlayerDao
import com.example.myquiz.model.PlayerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(app, PlayerDatabase::class.java, "player.sqlite")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    @Singleton
    @Provides
    fun provideDao(db: PlayerDatabase) = db.playerDao()

    @Singleton
    @Provides
    fun provideRepository(dao: PlayerDao) = LeaderBordRepository(dao)
}