package com.example.dimic.medicaltest.data.server.api

import com.example.dimic.medicaltest.data.server.request.LoginRequest
import com.example.dimic.medicaltest.data.server.request.RegistrationRequest
import com.example.dimic.medicaltest.data.server.responce.*
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.HttpException

class TestApi(private val apiMethods: TestApiMethods) {

    private val jsonMapper = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    fun getSpecialities(): Single<SpecialitiesResponce> = apiMethods
            .getSpecialities()
            .onErrorMapException()

    fun getCourses(specCode: Int): Single<CoursesResponce> = apiMethods
            .getCourses(specCode)
            .onErrorMapException()

    fun getSubjects(courseCode: Int): Single<SubjectsResponse> = apiMethods
            .getSubjects(courseCode)
            .onErrorMapException()

    fun getFiles(subjectCode: Int): Single<FilesResponce> = apiMethods
            .getFiles(subjectCode)
            .onErrorMapException()

    fun getTestQuestions(code: Int, count: Int, start: Int): Single<TestQuestionsResponse> = apiMethods
            .getTestQuestion(code, count, start)
            .onErrorMapException()

    fun createExam(name: String, group: Int, file: Int, time: Int, marResult: Int, count: Int): Single<BaseTestResponse> = apiMethods
            .createExam(name, group, file, time, marResult, count)
            .onErrorMapException()

    fun getExams(): Single<ExamsResponce> = apiMethods
            .getExams()
            .onErrorMapException()

    fun register(email: String, login: String, password: String, speciality: Int): Single<BaseTestResponse> = apiMethods
            .register(RegistrationRequest(email, login, password, speciality.toString()))
            .onErrorMapException()

    fun login(login: String, password: String): Single<LoginResponce> = apiMethods
            .login(LoginRequest(password, login))
            .onErrorMapException()

    private fun <T> Single<T>.onErrorMapException() = onErrorResumeNext { error ->
        (error as? HttpException)
                ?.response()?.errorBody()
                ?.let { parseErrorBody(it)?.responseCode }
                ?.let { TestApiConstant.ErrorCode.getErrorForCode(it) }
                .let { it?.toException() ?: error }
                .let { Single.error(it) }
    }

    private fun parseErrorBody(response: ResponseBody): ErrorResponse? {
        var error: ErrorResponse? = null
        try {
            error = jsonMapper.readValue(response.string(), ErrorResponse::class.java)
        } catch (e: Exception) {

        }
        return error
    }
}