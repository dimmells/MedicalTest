package com.example.dimic.medicaltest.interact

import com.example.dimic.medicaltest.data.CourseEntity
import com.example.dimic.medicaltest.data.server.TestService
import io.reactivex.Single

class CourseInteract(private val testService: TestService) {

    fun getCourses(specCode: Int): Single<List<CourseEntity>> = testService.getCourses(specCode)
            .map { response ->
                ArrayList<CourseEntity>().apply {
                    addAll(response)
                }
            }
}