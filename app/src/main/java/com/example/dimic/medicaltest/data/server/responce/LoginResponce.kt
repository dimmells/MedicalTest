package com.example.dimic.medicaltest.data.server.responce

import com.fasterxml.jackson.annotation.JsonProperty

class LoginResponce {

    @JsonProperty("user")
    val user: UserResponceEntity = UserResponceEntity()
}

class UserResponceEntity {

    @JsonProperty("login")
    val login = ""

    @JsonProperty("specCode")
    val specCode = 0

    @JsonProperty("course")
    val course = 0

    @JsonProperty("token")
    val token = ""

}