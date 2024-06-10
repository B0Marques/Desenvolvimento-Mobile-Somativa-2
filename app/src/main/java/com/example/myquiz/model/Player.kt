package com.example.myquiz.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "players")
data class Player(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nome: String,
    val pontos: Int
)
