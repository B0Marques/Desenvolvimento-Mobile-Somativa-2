package com.example.myquiz.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.myquiz.R
import com.example.myquiz.databinding.ActivityMainMenuBinding

class MainMenuActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.LeaderboardButton.setOnClickListener {
            //Go To Leaderboard theme select
            val intent = Intent(this, ThemeSelectorActivity::class.java)
            intent.putExtra("select_value",1)
            startActivity(intent)
        }
        binding.NewGameButton.setOnClickListener {
            val intent = Intent(this, ThemeSelectorActivity::class.java)
            intent.putExtra("select_value",0)
            startActivity(intent)
        }
    }


}