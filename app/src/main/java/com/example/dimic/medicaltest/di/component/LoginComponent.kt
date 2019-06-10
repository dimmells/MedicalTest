package com.example.dimic.medicaltest.di.component

import com.example.dimic.medicaltest.di.module.LoginModule
import com.example.dimic.medicaltest.di.scope.ViewScope
import com.example.dimic.medicaltest.presentation.presenter.LoginPresenter
import dagger.Subcomponent

@Subcomponent(modules = [LoginModule::class])
@ViewScope
interface LoginComponent {

    fun loginPresenter(): LoginPresenter
}