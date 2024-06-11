package com.example.myquiz.model

import com.google.gson.annotations.SerializedName

data class QuizResponse(
    @SerializedName("response_code") val responseCode: Int,
    @SerializedName("results") val resultados: List<Question>
)
