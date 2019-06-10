package com.example.dimic.medicaltest.data.server.responce

import com.fasterxml.jackson.annotation.JsonProperty

class ExamsResponce: BaseTestResponse() {

    @JsonProperty("")
    val exams: ArrayList<ExamResponceEntity> = ArrayList()
}

class ExamResponceEntity {

    @JsonProperty("id")
    val id = 0

    @JsonProperty("name")
    val name = ""

    @JsonProperty("question_count")
    val questionCount = 0

    @JsonProperty("max_result")
    val maxResult = 0

    @JsonProperty("file_code")
    val fileCode = 0

}