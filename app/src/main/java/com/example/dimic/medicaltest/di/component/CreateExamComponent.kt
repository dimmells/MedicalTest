package com.example.dimic.medicaltest.di.component

import com.example.dimic.medicaltest.di.module.CreateExamModule
import com.example.dimic.medicaltest.di.scope.ViewScope
import com.example.dimic.medicaltest.presentation.presenter.CreateExamPresenter
import dagger.Subcomponent

@Subcomponent(modules = [CreateExamModule::class])
@ViewScope
interface CreateExamComponent {

    fun createExamPresenter(): CreateExamPresenter
}