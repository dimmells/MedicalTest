package com.example.dimic.medicaltest.di.component

import com.example.dimic.medicaltest.di.module.SpecialityModule
import com.example.dimic.medicaltest.di.scope.ViewScope
import com.example.dimic.medicaltest.presentation.presenter.SpecialityPresenter
import dagger.Subcomponent

@Subcomponent(modules = [SpecialityModule::class])
@ViewScope
interface SpecialityComponent {

    fun specialityPresenter(): SpecialityPresenter
}