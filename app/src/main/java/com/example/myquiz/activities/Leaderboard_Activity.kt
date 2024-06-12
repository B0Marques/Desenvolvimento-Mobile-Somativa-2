package com.example.myquiz.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myquiz.AppModule
import com.example.myquiz.DicionarioTemas
import com.example.myquiz.LeaderboardAdapter
import com.example.myquiz.R
import com.example.myquiz.databinding.ActivityLeaderboardBinding
import com.example.myquiz.model.LeaderBordRepository
import com.example.myquiz.model.Player
import javax.inject.Inject

class Leaderboard_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityLeaderboardBinding



    @Inject
    lateinit var leaderBordRepository: LeaderBordRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeaderboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val questionTheme = intent.getIntExtra("theme",0)
        leaderBordRepository = AppModule().provideRepository(AppModule().provideDao(AppModule().provideDatabase(this)))

        binding.LeaderboardName.text = "Leaderboard: ${DicionarioTemas.GetThemeById(questionTheme)}"
        val players = leaderBordRepository.getByTheme(questionTheme).take(10)
        var i =1
        for(player in players){
            Log.d("DEBUG","Player ${i}, ${player.nome}, ${player.pontos}")
            i++
        }

        val adapter = LeaderboardAdapter(players)
        binding.leaderboardRecyclerview.layoutManager = GridLayoutManager(this,1)
        binding.leaderboardRecyclerview.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
    }
}