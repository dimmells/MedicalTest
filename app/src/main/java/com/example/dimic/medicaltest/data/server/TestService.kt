package com.example.dimic.medicaltest.data.server

import com.example.dimic.medicaltest.data.CourseEntity
import com.example.dimic.medicaltest.data.FileEntity
import com.example.dimic.medicaltest.data.QuestionEntity
import com.example.dimic.medicaltest.data.SpecialityEntity
import com.example.dimic.medicaltest.data.server.api.TestApi
import com.example.dimic.medicaltest.data.server.responce.ExamResponceEntity
import com.example.dimic.medicaltest.data.server.responce.ExamsResponce
import com.example.dimic.medicaltest.data.user.UserEntity
import com.example.dimic.medicaltest.data.user.UserManager
import io.reactivex.Completable
import io.reactivex.Single
import kotlin.collections.ArrayList

class TestService(private val api: TestApi, private val userManager: UserManager) {

    fun getSpecialities(): Single<List<SpecialityEntity>> = api.getSpecialities()
            .map { it.specialities }
            .map { response ->
                ArrayList<SpecialityEntity>().apply {
                    response.forEach { responseItem ->
                        add(SpecialityEntity(responseItem.code, responseItem.name)) }
                }
            }

    fun getCourses(specCode: Int): Single<List<CourseEntity>> = api.getCourses(specCode)
            .map { it.courses }
            .map { responce ->
                ArrayList<CourseEntity>().apply {
                    responce.forEach { responceItem ->
                        add(CourseEntity(responceItem.specCode, responceItem.courseCode, responceItem.groupName))
                    }
                }
            }

    fun getSubjects(courseCode: Int): Single<List<SpecialityEntity>> = api.getSubjects(courseCode)
            .map { it.subjects }
            .map { response ->
                ArrayList<SpecialityEntity>().apply {
                    response.forEach { responseItem ->
                        add(SpecialityEntity(responseItem.code, responseItem.name))
                    }
                }
            }

    fun getFiles(subjectCode: Int): Single<List<FileEntity>> = api.getFiles(subjectCode)
            .map { it.subjects }
            .map { response ->
                ArrayList<FileEntity>().apply {
                    response.forEach { responseItem ->
                        add(FileEntity(responseItem.subjectCode, responseItem.fileCode, responseItem.name))
                    }
                }
            }

    fun getTestQuestions(code: Int, count: Int, start: Int): Single<List<QuestionEntity>> =
            api.getTestQuestions(code, count, start)
                    .map { it.questions }
                    .map { response ->
                        ArrayList<QuestionEntity>().apply {
                            response.forEach { responseItem ->
                                add(QuestionEntity(
                                        responseItem.question,
                                        parseWrongAnswers(responseItem.wrongAnswer),
                                        responseItem.trueAnswer)
                                )
                            }
                        }
                    }

    fun createCategory(name: String, group: Int, file: Int, time: Int, marResult: Int, count: Int): Completable = api
            .createExam(name, group, file, time, marResult, count)
            .ignoreElement()

    fun getExams(): Single<ArrayList<ExamResponceEntity>> = api
            .getExams()
            .map { it.exams }

    fun register(email: String, login: String, password: String, speciality: Int): Completable = api
            .register(email, login, password, speciality)
            .ignoreElement()

    fun login(login: String, password: String): Completable = api
            .login(login, password)
            .map { responce ->
                UserEntity(
                        responce.user.login,
                        responce.user.specCode,
                        responce.user.course,
                        responce.user.token
                )
            }
            .doOnSuccess { userManager.update(it) }
            .ignoreElement()

    private fun parseWrongAnswers(json: String): ArrayList<String> {
        val wrongAnswer = ArrayList<String>()
        json.substring(1, json.lastIndex).split("|").forEach {
            if (!it.isEmpty())
                wrongAnswer.add(it)
        }

        return wrongAnswer
    }
}