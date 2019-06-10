package com.example.dimic.medicaltest.data.server.request

import com.fasterxml.jackson.annotation.JsonProperty

class LoginRequest(

        @field:JsonProperty("password")
        val password: String,

        @field:JsonProperty("login")
        val login: String

): BaseRequest()