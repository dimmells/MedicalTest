package com.example.dimic.medicaltest.di.module

import com.example.dimic.medicaltest.data.server.TestService
import com.example.dimic.medicaltest.di.scope.ViewScope
import com.example.dimic.medicaltest.interact.CourseInteract
import com.example.dimic.medicaltest.presentation.presenter.CoursePresenter
import dagger.Module
import dagger.Provides

@Module
class CourseModule {

    @Provides
    @ViewScope
    fun provideCourseInteract(testService: TestService): CourseInteract = CourseInteract(testService)

    @Provides
    @ViewScope
    fun provideCoursePresenter(courseInteract: CourseInteract): CoursePresenter = CoursePresenter(courseInteract)
}