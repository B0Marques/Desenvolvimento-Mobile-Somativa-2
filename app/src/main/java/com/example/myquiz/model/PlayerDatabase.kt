package com.example.myquiz.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Player::class], version = 1)
abstract class PlayerDatabase: RoomDatabase() {
    abstract fun playerDao(): PlayerDao
}