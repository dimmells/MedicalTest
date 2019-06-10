package com.example.dimic.medicaltest.di.component

import com.example.dimic.medicaltest.di.module.MainModule
import com.example.dimic.medicaltest.di.scope.ViewScope
import com.example.dimic.medicaltest.presentation.presenter.MainPresenter
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
@ViewScope
interface MainComponent {

    fun mainPresenter(): MainPresenter
}