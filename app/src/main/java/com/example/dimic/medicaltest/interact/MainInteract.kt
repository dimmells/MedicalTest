package com.example.dimic.medicaltest.interact

import com.example.dimic.medicaltest.data.user.UserManager

class MainInteract(private val userManager: UserManager) {

    fun isUserAuthorized() = userManager.userEntity.isAuthorized()
}