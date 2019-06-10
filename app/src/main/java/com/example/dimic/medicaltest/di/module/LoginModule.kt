package com.example.dimic.medicaltest.di.module

import com.example.dimic.medicaltest.data.server.TestService
import com.example.dimic.medicaltest.data.user.UserManager
import com.example.dimic.medicaltest.di.scope.ViewScope
import com.example.dimic.medicaltest.interact.LoginInteract
import com.example.dimic.medicaltest.presentation.presenter.LoginPresenter
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides
    @ViewScope
    fun provideLoginInteract(testService: TestService, userManager: UserManager): LoginInteract = LoginInteract(testService, userManager)

    @Provides
    @ViewScope
    fun provideLoginPresenter(loginInteract: LoginInteract): LoginPresenter = LoginPresenter(loginInteract)
}