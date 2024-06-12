package com.example.myquiz.model

import javax.inject.Inject

class LeaderBordRepository @Inject constructor(
    private val playerDao: PlayerDao
) {
    var players = playerDao.getAllPlayers()
    fun add(nome: String, pontos: Int, tema: Int) {
        val player = Player(nome = nome, pontos = pontos, tema = tema)
        playerDao.insertPlayer(player)
        players = playerDao.getAllPlayers()
    }


    fun getByTheme(theme:Int):List<Player>{
        return playerDao.getAllByTheme(theme)
    }
}