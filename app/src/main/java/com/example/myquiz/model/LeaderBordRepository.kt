package com.example.myquiz.model

import javax.inject.Inject

class LeaderBordRepository @Inject constructor(
    private val playerDao: PlayerDao
) {
    var players = playerDao.getAllPlayers()
    fun add(nome: String, pontos: Int) {
        val player = Player(nome = nome, pontos = pontos)
        playerDao.insertPlayer(player)
        players = playerDao.getAllPlayers()
    }
}