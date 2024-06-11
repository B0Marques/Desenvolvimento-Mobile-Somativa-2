package com.example.myquiz.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.myquiz.R
import com.example.myquiz.databinding.ActivityMainBinding
import com.example.myquiz.viewmodel.LeaderBoardViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: LeaderBoardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        )
        binding.id.text = "1"
        //addPlayer("Bruno", 10, 20)
        binding.id.text = "2"
//        var mainMenu = Intent(this, MainMenuActivity::class.java);
//        startActivity(mainMenu)
    }
    fun addPlayer(nome: String, pontos: Int, tema: Int) {
        viewModel.add(nome, pontos, tema)
    }
}