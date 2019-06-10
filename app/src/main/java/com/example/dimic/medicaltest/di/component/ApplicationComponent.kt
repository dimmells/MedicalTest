package com.example.dimic.medicaltest.di.component

import com.example.dimic.medicaltest.di.module.*
import com.example.dimic.medicaltest.di.scope.ApplicationScope
import dagger.Component

@Component(
        modules = [
            ApplicationModule::class,
            TestServiceModule::class,
            UserManagerModule::class
        ]
)
@ApplicationScope
interface ApplicationComponent {

    fun mainComponent(mainModule: MainModule): MainComponent

    fun specialityComponent(specialityModule: SpecialityModule): SpecialityComponent

    fun courseComponent(courseModule: CourseModule): CourseComponent

    fun subjectComponent(subjectModule: SubjectModule): SubjectComponent

    fun fileComponent(fileModule: FileModule): FileComponent

    fun testComponent(testModule: TestModule): TestComponent

    fun registrationComponent(registrationModule: RegistrationModule): RegistrationComponent

    fun loginComponent(loginModule: LoginModule): LoginComponent

    fun menuComponent(menuModule: MenuModule): MenuComponent

    fun createExamComponent(createExamModule: CreateExamModule): CreateExamComponent

    fun examListComponent(examListModule: ExamListModule): ExamListComponent
}




