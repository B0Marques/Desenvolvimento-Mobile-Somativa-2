package com.example.myquiz.model

import androidx.room.Database

@Database(entities = [Player::class], version = 1)
abstract class PlayerDatabase {
    abstract fun playerDao(): PlayerDao
}