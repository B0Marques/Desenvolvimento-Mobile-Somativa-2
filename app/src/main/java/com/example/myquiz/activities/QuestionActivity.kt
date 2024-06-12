package com.example.myquiz.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Chronometer
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.myquiz.AppModule
import com.example.myquiz.DicionarioTemas
import com.example.myquiz.model.QuizClient
import kotlinx.coroutines.launch
import com.example.myquiz.databinding.ActivityQuestionBinding
import com.example.myquiz.model.LeaderBordRepository
import com.example.myquiz.model.Player
import com.example.myquiz.model.Question
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

class QuestionActivity : AppCompatActivity() {
    private lateinit var binding:ActivityQuestionBinding
    private var questions:List<Question> = mutableListOf()

    private var currentQuestion:Int = 0
    private var questionTheme:Int = 20
    private var rightQuestionLocation:Int = 0

    private lateinit var handler:Handler
    private lateinit var runnable: Runnable
    private lateinit var chronometer: Chronometer


    private var pointsToAdd=0

    private var firstTime:Boolean = true

    private var playerName:String = "Joe"

    @Inject
    lateinit var leaderBordRepository: LeaderBordRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler = Handler(Looper.getMainLooper())

        questionTheme = intent.getIntExtra("theme",20)

        loadQuestions(questionTheme)


        leaderBordRepository = AppModule().provideRepository(AppModule().provideDao(AppModule().provideDatabase(this)))

        binding.startGame.setOnClickListener{
            if(binding.editTextNome.text.toString() != "") {
                Log.d("DEBUG","Start Game")
                playerName = binding.editTextNome.text.toString()
                showQuestion(questions[currentQuestion])
                Log.d("DEBUG", "Nome: ${playerName}")
                }
            else{
                    Log.d("DEBUG", "Nao tem como começar")
                }
            }




        //ShowQuestion(questions[currentQuestion])


        setupAnswerButtons()
    }
    private fun setupAnswerButtons(){
        binding.answer1.setOnClickListener {
            checkAnswer(1)
        }
        binding.answer2.setOnClickListener {
            checkAnswer(2)
        }
        binding.answer3.setOnClickListener {
            checkAnswer(3)
        }
        binding.answer4.setOnClickListener {
            checkAnswer(4)
        }
    }
    private fun getQuestions(theme:Int){
        lifecycleScope.launch {
            val getEasyQuestions = QuizClient.getQuiz(10,theme, "easy")
            questions += getEasyQuestions.resultados

            delay(5000)
            val getMediumQuestions = QuizClient.getQuiz(10,theme, "medium")
            questions += getMediumQuestions.resultados

            delay(5000)
            val getHardQuestions = QuizClient.getQuiz(10,theme, "hard")
            questions += getHardQuestions.resultados

            questions = questions.shuffled()
        }
    }
    private fun loadQuestions(theme: Int){
        CoroutineScope(Dispatchers.IO).launch {
            getQuestions(theme)
            withContext(Dispatchers.Main){
                disableForm(false)
                showLoading(true)
                delay(5000)
                disableForm(true)
                showLoading(false)
            }

        }
    }
    private fun showLoading(show:Boolean){
        binding.loadingScreen.root.visibility = if(show) View.VISIBLE else View.GONE
    }
    private fun disableForm(show:Boolean){
        binding.playerForm.visibility=if(show) View.VISIBLE else View.GONE
    }

    private fun checkAnswer(button:Int){
        if(button == rightQuestionLocation){
            showToast("Right Answer")
            val answerTime:Long =  (SystemClock.elapsedRealtime() - chronometer.base)/1000

            pointsToAdd += getPoints(questions[currentQuestion].dif, answerTime.toInt())


            nextQuestion()
        }
        else{
            showToast("Wrong Answer")
            leaderBordRepository.add(playerName,pointsToAdd,questionTheme)
            Log.d("DEBUG","Leaderboard inserted user: ${playerName}, points: ${pointsToAdd}, theme id: ${questionTheme}")
            val intent = Intent(this, Leaderboard_Activity::class.java)
            intent.putExtra("theme",questionTheme)
            startActivity(intent)
        }
    }

    private fun nextQuestion(){
        currentQuestion += 1
        showQuestion(questions[currentQuestion])
    }
    private fun showQuestion(question: Question){
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main){
               if(binding.playerForm.visibility == View.VISIBLE) {
                   disableForm(false)
                    showScreen(true)
               }

            }

        }

        binding.questionText.text = question.decodeHtml().pq
        binding.themeIcon.setImageResource(DicionarioTemas.GetThemeIcon(questionTheme)!!)
        val answersToAdd = mutableListOf("","","","")
        rightQuestionLocation = Random.nextInt(1,4)
        var wrongQuestions = 0
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


        chronometer = Chronometer(this)
        chronometer.base = SystemClock.elapsedRealtime()
        chronometer.start()

    }
    private fun showScreen(show:Boolean){
        if(!show) {
            binding.questionText.visibility = View.GONE
            binding.card1.visibility = View.GONE
            binding.card2.visibility = View.GONE
            binding.card3.visibility = View.GONE
            binding.card4.visibility = View.GONE
            binding.themeIcon.visibility = View.GONE
        }else{
            binding.questionText.visibility = View.VISIBLE
            binding.card1.visibility = View.VISIBLE
            binding.card2.visibility = View.VISIBLE
            binding.card3.visibility = View.VISIBLE
            binding.card4.visibility = View.VISIBLE
            binding.themeIcon.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }
    private fun showToast(message:String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
    private fun getPoints(dif: String, tempo: Int): Int {
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
