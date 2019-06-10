package com.example.dimic.medicaltest.interact

import com.example.dimic.medicaltest.data.server.TestService
import com.example.dimic.medicaltest.data.user.UserEntity
import com.example.dimic.medicaltest.data.user.UserManager
import io.reactivex.Completable

class LoginInteract(private val testService: TestService, private val userManager: UserManager) {

    fun login(login: String, password: String): Completable = testService.login(login, password)

    fun getUser(): UserEntity = userManager.userEntity
}