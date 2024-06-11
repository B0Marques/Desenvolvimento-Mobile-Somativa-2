package com.example.myquiz.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myquiz.DicionarioTemas
import com.example.myquiz.model.LeaderBordRepository
import com.example.myquiz.model.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LeaderBoardViewModel @Inject constructor(private val lbRepository: LeaderBordRepository)
    : ViewModel() {
        val boardLiveData = MutableLiveData<List<Player>>()
    fun add(nome: String, pontos: Int, tema: Int) {
        lbRepository.add(nome, pontos, tema)
        refresh()
        Log.d("LeaderBoardViewModel", "Player adicionado: $nome, Pontos: $pontos, Tema: ${DicionarioTemas.GetThemeById(tema)}")
    }
    fun refresh() {
        boardLiveData.value = lbRepository.players
    }
}