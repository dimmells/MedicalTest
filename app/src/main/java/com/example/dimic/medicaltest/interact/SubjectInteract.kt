package com.example.dimic.medicaltest.interact

import com.example.dimic.medicaltest.data.SpecialityEntity
import com.example.dimic.medicaltest.data.server.TestService
import io.reactivex.Single

class SubjectInteract(private val testService: TestService) {

    fun getSubjects(courseCode: Int): Single<List<SpecialityEntity>> = testService.getSubjects(courseCode)
            .map { response ->
                ArrayList<SpecialityEntity>().apply {
                    addAll(response)
                }
            }
}