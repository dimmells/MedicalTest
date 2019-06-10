package com.example.dimic.medicaltest.data.user

data class UserEntity(
        var login: String? = null,
        var specCode: Int = 0,
        var course: Int = 0,
        var token: String? = null,
        var isTeacher: Boolean = false
) {

    fun isAuthorized(): Boolean = !token.isNullOrEmpty()
}