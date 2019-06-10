package com.example.dimic.medicaltest.data.user

import android.content.SharedPreferences

class UserDataStore(private val sharedPreferences: SharedPreferences) {

    companion object {
        const val KEY_LOGIN = "login"
        const val KEY_SPEC_CODE = "specCode"
        const val KEY_COURSE = "course"
        const val KEY_TOKEN = "token"
    }

    fun save(user: UserEntity) = with(user) {
        sharedPreferences.edit()
                .putString(KEY_LOGIN, login)
                .putInt(KEY_SPEC_CODE, specCode)
                .putInt(KEY_COURSE, course)
                .putString(KEY_TOKEN, token)
                .apply()
    }

    fun load(): UserEntity = UserEntity()
            .apply {
                login = sharedPreferences.getString(KEY_LOGIN, "")
                specCode = sharedPreferences.getInt(KEY_SPEC_CODE, 0)
                course = sharedPreferences.getInt(KEY_COURSE, 0)
                token = sharedPreferences.getString(KEY_TOKEN, "")
            }

    fun clear() = sharedPreferences.edit().clear().apply()
}