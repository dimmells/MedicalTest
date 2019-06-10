package com.example.dimic.medicaltest.di.module

import com.example.dimic.medicaltest.di.scope.ViewScope
import com.example.dimic.medicaltest.interact.TestInteract
import com.example.dimic.medicaltest.presentation.presenter.TestPresenter
import com.example.dimic.medicaltest.data.server.TestService
import dagger.Module
import dagger.Provides

@Module
class TestModule {

    @Provides
    @ViewScope
    fun provideTestInteract(testService: TestService) = TestInteract(testService)

    @Provides
    @ViewScope
    fun provideTestPresenter(testInteract: TestInteract) = TestPresenter(testInteract)
}