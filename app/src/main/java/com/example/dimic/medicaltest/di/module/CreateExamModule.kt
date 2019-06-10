package com.example.dimic.medicaltest.di.module

import com.example.dimic.medicaltest.data.server.TestService
import com.example.dimic.medicaltest.di.scope.ViewScope
import com.example.dimic.medicaltest.interact.CreateExamInteract
import com.example.dimic.medicaltest.presentation.presenter.CreateExamPresenter
import dagger.Module
import dagger.Provides

@Module
class CreateExamModule {

    @Provides
    @ViewScope
    fun provideCreateExamInteract(testService: TestService): CreateExamInteract = CreateExamInteract(testService)

    @Provides
    @ViewScope
    fun provideCreateExamPresenter(createExamInteract: CreateExamInteract): CreateExamPresenter = CreateExamPresenter(createExamInteract)
}