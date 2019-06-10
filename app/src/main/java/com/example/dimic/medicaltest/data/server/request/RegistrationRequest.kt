package com.example.dimic.medicaltest.data.server.request

import com.fasterxml.jackson.annotation.JsonProperty

class RegistrationRequest(

        @field:JsonProperty("email")
        val email: String,

        @field:JsonProperty("login")
        val login: String,

        @field:JsonProperty("password")
        val password: String,

        @field:JsonProperty("speciality")
        val speciality: String

): BaseRequest()
