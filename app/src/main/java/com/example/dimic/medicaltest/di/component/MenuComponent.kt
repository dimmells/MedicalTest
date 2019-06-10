package com.example.dimic.medicaltest.di.component

import com.example.dimic.medicaltest.di.module.MenuModule
import com.example.dimic.medicaltest.di.scope.ViewScope
import com.example.dimic.medicaltest.presentation.presenter.MenuPresenter
import dagger.Subcomponent

@Subcomponent(modules = [MenuModule::class])
@ViewScope
interface MenuComponent {

    fun menuPresenter(): MenuPresenter
}