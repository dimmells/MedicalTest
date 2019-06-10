package com.example.dimic.medicaltest.di.component

import com.example.dimic.medicaltest.di.module.ExamListModule
import com.example.dimic.medicaltest.di.scope.ViewScope
import com.example.dimic.medicaltest.presentation.presenter.ExamListPresenter
import dagger.Subcomponent

@Subcomponent(modules = [ExamListModule::class])
@ViewScope
interface ExamListComponent {

    fun examListPresenter(): ExamListPresenter
}