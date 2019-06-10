package com.example.dimic.medicaltest.di.component

import com.example.dimic.medicaltest.di.module.SubjectModule
import com.example.dimic.medicaltest.di.scope.ViewScope
import com.example.dimic.medicaltest.presentation.presenter.SubjectPresenter
import dagger.Subcomponent

@Subcomponent(modules = [SubjectModule::class])
@ViewScope
interface SubjectComponent {

    fun subjectPresenter(): SubjectPresenter
}