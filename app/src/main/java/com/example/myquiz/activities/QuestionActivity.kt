package com.example.myquiz.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.example.myquiz.DicionarioTemas
import com.example.myquiz.R
import com.example.myquiz.model.QuizClient
import kotlinx.coroutines.launch
import com.example.myquiz.databinding.ActivityQuestionBinding
import com.example.myquiz.model.Question
import kotlin.random.Random

class QuestionActivity : AppCompatActivity() {
    lateinit var binding:ActivityQuestionBinding
    private lateinit var questions:List<Question>
    private var currentQuestion:Int = 0
    private var questionTheme:Int = 20;
    private lateinit var buttons:List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)



        lifecycleScope.launch {
            val getQuestions = QuizClient.getQuiz(10,questionTheme, "easy")
            //binding.id.text = result.resultados[0].decodeHtml().pq
            questions = getQuestions.resultados
            ShowQuestion(questions[currentQuestion])
        }



        //ShowQuestion(questions[currentQuestion])



    }
    fun ShowQuestion(question: Question){
        binding.questionText.text = question.decodeHtml().pq
        binding.themeIcon.setImageResource(DicionarioTemas.GetThemeIcon(questionTheme)!!)
        var answersToAdd = mutableListOf<String>("","","","")
        val rightQuestionLocation = Random.nextInt(1,4)
        var wrongQuestions:Int = 0;
        for(i in 1..4){
            if(i == rightQuestionLocation) {
                answersToAdd.add(i,question.decodeHtml().correta)
            }
            else{
                answersToAdd.add(i,question.decodeHtml().falsas[wrongQuestions])
                wrongQuestions+=1
            }

        }

        binding.answer1.text = answersToAdd[1]
        binding.answer2.text = answersToAdd[2]
        binding.answer3.text = answersToAdd[3]
        binding.answer4.text = answersToAdd[4]


    }
    fun showToast(message:String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
    fun getPoints(dif: String, tempo: Int): Int {
        val pontosBase = when (dif) {
            "easy" -> 10
            "medium" -> 20
            "hard" -> 30
            else -> throw IllegalArgumentException("Dificuldade inválida")
        }
            val fatorTempo = if (tempo < 10) 2 else 1
            return pontosBase * fatorTempo
        }
    }
