package com.example.dimic.medicaltest.data.server.responce

import com.fasterxml.jackson.annotation.JsonProperty

open class BaseTestResponse {

    @JsonProperty("code")
    var responseCode = 0
}