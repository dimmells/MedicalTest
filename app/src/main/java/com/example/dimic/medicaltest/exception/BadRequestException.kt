package com.example.dimic.medicaltest.exception

import com.example.dimic.medicaltest.data.server.api.TestApiConstant

open class BadRequestException(code : TestApiConstant.ErrorCode) : TestApiException(code)
