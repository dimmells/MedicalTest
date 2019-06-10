package com.example.dimic.medicaltest.di.module

import com.example.dimic.medicaltest.data.user.UserManager
import com.example.dimic.medicaltest.di.scope.ViewScope
import com.example.dimic.medicaltest.interact.MainInteract
import com.example.dimic.medicaltest.presentation.presenter.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    @ViewScope
    fun provideMainInteract(userManager: UserManager): MainInteract = MainInteract(userManager)

    @Provides
    @ViewScope
    fun provideMainPresenter(mainInteract: MainInteract): MainPresenter = MainPresenter(mainInteract)
}