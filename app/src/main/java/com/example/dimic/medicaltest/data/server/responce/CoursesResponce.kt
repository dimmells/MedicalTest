package com.example.dimic.medicaltest.data.server.responce

import com.fasterxml.jackson.annotation.JsonProperty

class CoursesResponce: BaseTestResponse() {

    @JsonProperty("courses")
    val courses: ArrayList<CourseResponceEntity> = ArrayList()
}

class CourseResponceEntity {

    @JsonProperty("specCode")
    val specCode = 0

    @JsonProperty("courseCode")
    val courseCode = 0

    @JsonProperty("groupName")
    val groupName = ""

}