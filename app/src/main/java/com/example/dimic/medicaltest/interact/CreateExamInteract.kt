package com.example.dimic.medicaltest.interact

import com.example.dimic.medicaltest.data.server.TestService
import io.reactivex.Completable

class CreateExamInteract(private val testService: TestService) {

    fun getSpecialities() = testService.getSpecialities()

    fun getCourses(specCode: Int) = testService.getCourses(specCode)

    fun getSubjects(courseCode: Int) = testService.getSubjects(courseCode)

    fun getFiles(subjectCode: Int) = testService.getFiles(subjectCode)

    fun createExam(name: String, group: Int, file: Int, time: Int, marResult: Int, count: Int): Completable =
            testService.createCategory(name, group, file, time, marResult, count)
}