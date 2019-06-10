package com.example.dimic.medicaltest.data.server.api

import com.example.dimic.medicaltest.data.server.request.LoginRequest
import com.example.dimic.medicaltest.data.server.request.RegistrationRequest
import com.example.dimic.medicaltest.data.server.responce.*
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface TestApiMethods {

    @GET(TestApiConstant.PATH_SPECIALITIES)
    fun getSpecialities(): Single<SpecialitiesResponce>

    @GET(TestApiConstant.PATH_COURSES)
    fun getCourses(@Query("code") specCode: Int): Single<CoursesResponce>

    @GET(TestApiConstant.PATH_SUBJECTS)
    fun getSubjects(@Query("code") courseCode: Int): Single<SubjectsResponse>

    @GET(TestApiConstant.PATH_FILES)
    fun getFiles(@Query("code") subjectCode: Int): Single<FilesResponce>

    @GET(TestApiConstant.PATH_TEST_QUESTIONS)
    fun getTestQuestion(@Query("code") code: Int, @Query("count") count: Int, @Query("start") start: Int): Single<TestQuestionsResponse>

    @GET(TestApiConstant.PATH_CREATE_EXAM)
    fun createExam(@Query("name") name: String, @Query("group") group: Int, @Query("file") file: Int, @Query("time") time: Int, @Query("max_result") marResult: Int, @Query("count") count: Int): Single<BaseTestResponse>

    @GET(TestApiConstant.PATH_GET_EXAMS)
    fun getExams(): Single<ExamsResponce>

    @POST(TestApiConstant.PATH_REGISTRATION)
    fun register(@Body body: RegistrationRequest): Single<BaseTestResponse>

    @POST(TestApiConstant.PATH_AUTHORIZATION)
    fun login(@Body body: LoginRequest): Single<LoginResponce>
}