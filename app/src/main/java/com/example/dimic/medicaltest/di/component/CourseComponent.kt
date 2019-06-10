package com.example.dimic.medicaltest.di.component

import com.example.dimic.medicaltest.di.module.CourseModule
import com.example.dimic.medicaltest.di.scope.ViewScope
import com.example.dimic.medicaltest.presentation.presenter.CoursePresenter
import dagger.Subcomponent

@Subcomponent(modules = [CourseModule::class])
@ViewScope
interface CourseComponent {

    fun coursePresenter(): CoursePresenter
}