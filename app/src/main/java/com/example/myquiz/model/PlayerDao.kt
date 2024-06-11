package com.example.myquiz.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
interface PlayerDao {
    @Insert
    fun insertPlayer(player: Player)

    @Query("SELECT * FROM players ORDER BY pontos DESC")
    fun getAllPlayers(): List<Player>
}