package com.example.dimic.medicaltest.di.component

import com.example.dimic.medicaltest.di.module.RegistrationModule
import com.example.dimic.medicaltest.di.scope.ViewScope
import com.example.dimic.medicaltest.presentation.presenter.RegistrationPresenter
import dagger.Subcomponent

@Subcomponent(modules = [RegistrationModule::class])
@ViewScope
interface RegistrationComponent {

    fun registrationPresenter(): RegistrationPresenter
}