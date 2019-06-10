package com.example.dimic.medicaltest.interact

import com.example.dimic.medicaltest.data.user.UserEntity
import com.example.dimic.medicaltest.data.user.UserManager

class MenuInteract(private val userManager: UserManager) {

    fun getUser(): UserEntity = userManager.userEntity

    fun updateUser(user: UserEntity) = userManager.update(user)
}