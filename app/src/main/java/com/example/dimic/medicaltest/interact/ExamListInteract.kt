package com.example.dimic.medicaltest.interact

import com.example.dimic.medicaltest.data.server.TestService

class ExamListInteract(private val testService: TestService) {

    fun getExams() = testService.getExams()
}