package com.example.dimic.medicaltest.data.server.responce

import com.fasterxml.jackson.annotation.JsonProperty

class SpecialitiesResponce: BaseTestResponse() {

    @JsonProperty("specialities")
    val specialities: ArrayList<SpecialityResponceEntity> = ArrayList()
}

class SpecialityResponceEntity {

    @JsonProperty("specCode")
    val code = 0

    @JsonProperty("specName")
    val name = ""

}