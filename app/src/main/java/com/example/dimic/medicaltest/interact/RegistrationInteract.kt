package com.example.dimic.medicaltest.interact

import com.example.dimic.medicaltest.data.server.TestService
import io.reactivex.Completable

class RegistrationInteract(private val testService: TestService) {

    fun register(email: String, login: String, password: String, speciality: Int): Completable =
            testService.register(email, login, password, speciality)
}