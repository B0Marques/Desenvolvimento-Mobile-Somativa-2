package com.example.myquiz.model

import retrofit2.http.GET
import retrofit2.http.Query

interface QuizAPI {
    @GET("/api.php")
    suspend fun getQuiz(
        @Query("amount") qtd: Int,
        @Query("category") categoria: Int,
        @Query("difficulty") dif: String,
        @Query("type") tipo: String? = "multiple",
        ): List<Question>
}