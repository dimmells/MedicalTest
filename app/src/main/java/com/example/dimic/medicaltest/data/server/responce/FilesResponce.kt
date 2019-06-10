package com.example.dimic.medicaltest.data.server.responce

import com.fasterxml.jackson.annotation.JsonProperty

class FilesResponce: BaseTestResponse() {

    @JsonProperty("files")
    val subjects: ArrayList<FileResponceEntity> = ArrayList()
}

class FileResponceEntity {

    @JsonProperty("subjectCode")
    val subjectCode = 0

    @JsonProperty("fileCode")
    val fileCode = 0

    @JsonProperty("fileName")
    val name = ""

}