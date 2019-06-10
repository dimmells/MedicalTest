package com.example.dimic.medicaltest.di.module

import com.example.dimic.medicaltest.data.server.TestService
import com.example.dimic.medicaltest.di.scope.ViewScope
import com.example.dimic.medicaltest.interact.RegistrationInteract
import com.example.dimic.medicaltest.presentation.presenter.RegistrationPresenter
import dagger.Module
import dagger.Provides

@Module
class RegistrationModule {

    @Provides
    @ViewScope
    fun provideRegistrationInteract(testService: TestService): RegistrationInteract = RegistrationInteract(testService)

    @Provides
    @ViewScope
    fun provideRegistrationPresenter(registrationInteract: RegistrationInteract): RegistrationPresenter = RegistrationPresenter(registrationInteract)
}