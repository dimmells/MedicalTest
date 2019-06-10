package com.example.dimic.medicaltest.data.server.responce

import com.fasterxml.jackson.annotation.JsonProperty


class TestQuestionsResponse : BaseTestResponse() {

    @JsonProperty("filequest")
    val questions: ArrayList<TestQuestionResponseEntity> = ArrayList()
}

class TestQuestionResponseEntity {

    @JsonProperty("id")
    var id = 0
    @JsonProperty("question")
    var question = ""
    @JsonProperty("wrongAnswer")
    var wrongAnswer = ""
    @JsonProperty("trueAnswer")
    var trueAnswer = ""

}