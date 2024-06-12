package com.example.myquiz.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myquiz.R
import com.example.myquiz.databinding.ActivityLeaderboardBinding

class Leaderboard_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityLeaderboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeaderboardBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}