package com.example.dimic.medicaltest.interact

import com.example.dimic.medicaltest.data.QuestionEntity
import com.example.dimic.medicaltest.data.server.TestService
import io.reactivex.Single

class TestInteract(private val testService: TestService) {

    fun getQuestions(code: Int, count: Int, start: Int): Single<List<QuestionEntity>> =
            testService.getTestQuestions(code, count, start)
                .map { response ->
                    ArrayList<QuestionEntity>().apply {
                        addAll(response)
                    }
                }
}