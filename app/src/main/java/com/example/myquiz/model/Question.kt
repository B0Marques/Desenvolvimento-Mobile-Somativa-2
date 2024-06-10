package com.example.myquiz.model

import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("difficulty") val dif: String,
    @SerializedName("question") val pq: String,
    @SerializedName("correct_answer") val correta: String,
    @SerializedName("incorrect_answers") val falsas: List<String>,
)
