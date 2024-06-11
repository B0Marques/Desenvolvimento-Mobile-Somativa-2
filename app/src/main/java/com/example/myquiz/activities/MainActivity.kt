package com.example.myquiz.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.myquiz.AppModule
import com.example.myquiz.R
import com.example.myquiz.databinding.ActivityMainBinding
import com.example.myquiz.model.LeaderBordRepository
import com.example.myquiz.viewmodel.LeaderBoardViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var leaderBordRepository:LeaderBordRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        )

        leaderBordRepository = AppModule().provideRepository(AppModule().provideDao(AppModule().provideDatabase(this)))


        var mainMenu = Intent(this, QuestionActivity::class.java);
        startActivity(mainMenu)
    }
    fun addPlayer(nome: String, pontos: Int, tema: Int) {
        leaderBordRepository.add(nome, pontos, tema)
    }
}