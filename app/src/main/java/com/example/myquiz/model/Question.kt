package com.example.myquiz.model

import android.text.Html
import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("difficulty") val dif: String,
    @SerializedName("question") val pq: String,
    @SerializedName("correct_answer") val correta: String,
    @SerializedName("incorrect_answers") val falsas: List<String>,
    @SerializedName("category") val tema: String
) {
    fun decodeHtml(): Question {
        return Question(
            dif,
            Html.fromHtml(pq).toString(),
            Html.fromHtml(correta).toString(),
            falsas.map { Html.fromHtml(it).toString() },
            Html.fromHtml(tema).toString()
        )
    }
}
