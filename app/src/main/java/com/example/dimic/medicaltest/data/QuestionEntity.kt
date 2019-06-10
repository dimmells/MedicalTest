package com.example.dimic.medicaltest.data

data class QuestionEntity(
        val question: String,
        val wrongAnswer: ArrayList<String>,
        val trueAnswer: String,
        var userAnswer: String?) {

    constructor(question: String, wrongAnswer: ArrayList<String>, trueAnswer: String):
            this(question, wrongAnswer, trueAnswer, null)
}