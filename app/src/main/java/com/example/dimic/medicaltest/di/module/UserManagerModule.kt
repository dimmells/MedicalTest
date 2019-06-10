package com.example.dimic.medicaltest.di.module

import android.content.Context
import android.content.SharedPreferences
import com.example.dimic.medicaltest.data.user.UserDataStore
import com.example.dimic.medicaltest.data.user.UserManager
import com.example.dimic.medicaltest.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

@Module
class UserManagerModule {

    @Provides
    @ApplicationScope
    @UserManagerQualifier
    fun provideSharedPreferences(context: Context): SharedPreferences = context.getSharedPreferences("UserManager", Context.MODE_PRIVATE)

    @Provides
    @ApplicationScope
    fun provideUserDataStore(@UserManagerQualifier sharedPreferences: SharedPreferences): UserDataStore = UserDataStore(sharedPreferences)

    @Provides
    @ApplicationScope
    fun provideUserManager(userDataStore: UserDataStore): UserManager = UserManager(userDataStore)
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class UserManagerQualifier