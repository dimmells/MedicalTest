package com.example.dimic.medicaltest.di.module

import com.example.dimic.medicaltest.data.server.TestService
import com.example.dimic.medicaltest.di.scope.ViewScope
import com.example.dimic.medicaltest.interact.SubjectInteract
import com.example.dimic.medicaltest.presentation.presenter.SubjectPresenter
import dagger.Module
import dagger.Provides

@Module
class SubjectModule {

    @Provides
    @ViewScope
    fun provideSubjectInteract(testService: TestService): SubjectInteract = SubjectInteract(testService)

    @Provides
    @ViewScope
    fun provideSubjectPresenter(subjectInteract: SubjectInteract):SubjectPresenter = SubjectPresenter(subjectInteract)

}