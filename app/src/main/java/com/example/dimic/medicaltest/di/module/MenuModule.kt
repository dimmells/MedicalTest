package com.example.dimic.medicaltest.di.module

import com.example.dimic.medicaltest.data.user.UserManager
import com.example.dimic.medicaltest.di.scope.ViewScope
import com.example.dimic.medicaltest.interact.MenuInteract
import com.example.dimic.medicaltest.presentation.presenter.MenuPresenter
import dagger.Module
import dagger.Provides

@Module
class MenuModule {

    @Provides
    @ViewScope
    fun provideMenuInteract(userManager: UserManager): MenuInteract = MenuInteract(userManager)

    @Provides
    @ViewScope
    fun provideMenuPresenter(menuInteract: MenuInteract): MenuPresenter = MenuPresenter(menuInteract)

}