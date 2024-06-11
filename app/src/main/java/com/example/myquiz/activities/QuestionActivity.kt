package com.example.myquiz.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import kotlinx.coroutines.Runnable
import kotlin.random.Random

class QuestionActivity : AppCompatActivity() {
    lateinit var binding:ActivityQuestionBinding
    private lateinit var questions:List<Question>
    private var currentQuestion:Int = 0
    private var questionTheme:Int = 20;
    private lateinit var buttons:List<Button>
    var rightQuestionLocation:Int = 0

    private var timer:Long = 0
    private lateinit var handler:Handler
    private lateinit var runnable: Runnable

    private var pointsToAdd=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler = Handler(Looper.getMainLooper())

        lifecycleScope.launch {
            val getQuestions = QuizClient.getQuiz(10,questionTheme, "easy")
            //binding.id.text = result.resultados[0].decodeHtml().pq
            questions = getQuestions.resultados
            ShowQuestion(questions[currentQuestion])
        }



        binding.answer1.setOnClickListener {
            CheckAnswer(1)
        }
        binding.answer2.setOnClickListener {
            CheckAnswer(2)
        }
        binding.answer3.setOnClickListener {
            CheckAnswer(3)
        }
        binding.answer4.setOnClickListener {
            CheckAnswer(4)
        }

        //ShowQuestion(questions[currentQuestion])

    }

    fun CheckAnswer(button:Int){
        if(button == rightQuestionLocation){
            showToast("Right Answer")
            NextQuestion()
            //pointsToAdd += getPoints(questions[currentQuestion].dif,timer)
            val answerTime:Long = ( System.currentTimeMillis()-timer)

            Log.d("Timer", "Question right answer on: ${System.currentTimeMillis()}, ${timer}")
            //Log.d("DEBUG", "Difference beetween: ${System.currentTimeMillis()} - ${timer} on time: ${answerTime}")

        }
        else{
            showToast("Wrong Answer")
        }
    }
    fun NextQuestion(){
        currentQuestion += 1
        ShowQuestion(questions[currentQuestion])
    }
    fun ShowQuestion(question: Question){
        binding.questionText.text = question.decodeHtml().pq
        binding.themeIcon.setImageResource(DicionarioTemas.GetThemeIcon(questionTheme)!!)
        var answersToAdd = mutableListOf<String>("","","","")
        rightQuestionLocation = Random.nextInt(1,4)
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

        timer = System.currentTimeMillis()
        Log.d("Timer", "Question started on: ${timer}")

    }
    
    override fun onDestroy() {
        super.onDestroy()
        // Remover callbacks do handler para evitar vazamentos de memória
        handler.removeCallbacks(runnable)
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
