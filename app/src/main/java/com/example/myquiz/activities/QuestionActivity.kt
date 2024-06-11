package com.example.myquiz.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.example.myquiz.R
import com.example.myquiz.model.QuizClient
import kotlinx.coroutines.launch
import com.example.myquiz.databinding.ActivityQuestionBinding
import com.example.myquiz.model.Question

class QuestionActivity : AppCompatActivity() {
    lateinit var binding:ActivityQuestionBinding
    private lateinit var questions:List<Question>
    private var currentQuestion:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        lifecycleScope.launch {
            val getQuestions = QuizClient.getQuiz(10,20, "easy")
            //binding.id.text = result.resultados[0].decodeHtml().pq
            questions = getQuestions.resultados
            ShowQuestion(questions[currentQuestion])
        }



        //ShowQuestion(questions[currentQuestion])



    }
    fun ShowQuestion(question: Question){
        binding.questionText.text = question.decodeHtml().pq
        var wrongQuests = question.decodeHtml().falsas
        
    }
    fun showToast(message:String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}