package com.example.dimic.medicaltest.data.server.api

import com.example.dimic.medicaltest.exception.BadRequestException
import com.example.dimic.medicaltest.exception.EmailAlreadyExistException
import com.example.dimic.medicaltest.exception.LoginAlreadyExistException
import com.example.dimic.medicaltest.exception.TestApiException

object TestApiConstant {

    const val BASE_URL = "http://mykolavoitovych.zzz.com.ua/tests/"

    const val PATH_REGISTRATION = "registration.php"

    const val PATH_AUTHORIZATION = "auterisation.php"

    const val PATH_SPECIALITIES = "controller.php?code"

    const val PATH_COURSES = "controller.php"

    const val PATH_SUBJECTS = "controller.php"

    const val PATH_FILES = "controller.php"

    const val PATH_TEST_QUESTIONS = "controller.php"

    const val PATH_CREATE_EXAM = "examsCreate.php"

    const val PATH_GET_EXAMS = "getExams.php"

    const val PATH_GET_EXAM_RESULT = "getResult.php"

    const val PATH_DELETE_EXAM = "deleteExam.php"

    const val PATH_SAVE_RESULT = "storeResult.php"

    const val PATH_LOGOUT = "exit.php"

    enum class ErrorCode(val code: Int) {

        UNKNOWN_ERROR(-1),

        LOGIN_ALREADY_EXIST(1) {
            override fun toException() = LoginAlreadyExistException(this)
        },

        EMAIL_ALREADY_EXIST(2) {
            override fun toException() = EmailAlreadyExistException(this)
        },

        NOT_PERFORMED(13) {
            override fun toException() = BadRequestException(this)
        };

        companion object {
            fun getErrorForCode(codeToIdentify: Int) = values().firstOrNull { it.code == codeToIdentify } ?: UNKNOWN_ERROR
        }

        open fun toException(): TestApiException = TestApiException(this)

    }

    enum class ResponseCode(val code: Int) {
        OK(0)
    }
}