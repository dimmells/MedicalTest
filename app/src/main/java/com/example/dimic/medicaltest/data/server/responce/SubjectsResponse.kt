package com.example.dimic.medicaltest.data.server.responce

import com.fasterxml.jackson.annotation.JsonProperty

class SubjectsResponse: BaseTestResponse() {

    @JsonProperty("subjects")
    val subjects: ArrayList<SubjectResponceEntity> = ArrayList()
}

class SubjectResponceEntity {

    @JsonProperty("subjectCode")
    val code = 0

    @JsonProperty("subjectName")
    val name = ""

}