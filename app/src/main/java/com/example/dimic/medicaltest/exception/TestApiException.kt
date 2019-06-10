package com.example.dimic.medicaltest.exception

import com.example.dimic.medicaltest.data.server.api.TestApiConstant

open class TestApiException(val code: TestApiConstant.ErrorCode) : Exception()
