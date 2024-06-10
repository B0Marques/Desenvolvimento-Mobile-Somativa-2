package com.example.myquiz.model

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuizClient {
    companion object {
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://opentdb.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        val api = retrofit.create(QuizAPI::class.java)

        suspend fun getQuiz (qtd: Int, categoria: Int, dif: String, tipo: String? = "multiple") =
            api.getQuiz(qtd, categoria, dif, tipo)
    }
}