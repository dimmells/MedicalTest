package com.example.dimic.medicaltest.di.module

import com.example.dimic.medicaltest.data.server.TestService
import com.example.dimic.medicaltest.di.scope.ViewScope
import com.example.dimic.medicaltest.interact.ExamListInteract
import com.example.dimic.medicaltest.presentation.presenter.ExamListPresenter
import dagger.Module
import dagger.Provides

@Module
class ExamListModule {

    @Provides
    @ViewScope
    fun provideExamListInteract(testService: TestService): ExamListInteract = ExamListInteract(testService)

    @Provides
    @ViewScope
    fun provideExamListPresenter(examListInteract: ExamListInteract): ExamListPresenter = ExamListPresenter(examListInteract)
}