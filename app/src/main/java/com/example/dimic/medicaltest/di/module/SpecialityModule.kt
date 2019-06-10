package com.example.dimic.medicaltest.di.module

import com.example.dimic.medicaltest.data.server.TestService
import com.example.dimic.medicaltest.di.scope.ViewScope
import com.example.dimic.medicaltest.interact.SpecialityInteract
import com.example.dimic.medicaltest.presentation.presenter.SpecialityPresenter
import dagger.Module
import dagger.Provides

@Module
class SpecialityModule {

    @Provides
    @ViewScope
    fun provideSpecialityInteract(testService: TestService): SpecialityInteract = SpecialityInteract(testService)

    @Provides
    @ViewScope
    fun provideSpecialityPresenter(specialityInteract: SpecialityInteract): SpecialityPresenter = SpecialityPresenter(specialityInteract)
}