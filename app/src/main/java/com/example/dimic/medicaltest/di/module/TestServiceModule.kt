package com.example.dimic.medicaltest.di.module

import com.example.dimic.medicaltest.di.scope.ApplicationScope
import com.example.dimic.medicaltest.data.server.TestService
import com.example.dimic.medicaltest.data.server.api.TestApi
import com.example.dimic.medicaltest.data.user.UserManager
import dagger.Module
import dagger.Provides

@Module(includes = [TestApiModule::class])
class TestServiceModule {

    @Provides
    @ApplicationScope
    fun provideTestService(testApi: TestApi, userManager: UserManager): TestService =
            TestService(testApi, userManager)
}