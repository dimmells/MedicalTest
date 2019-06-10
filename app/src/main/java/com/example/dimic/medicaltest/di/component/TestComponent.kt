package com.example.dimic.medicaltest.di.component

import com.example.dimic.medicaltest.di.module.TestModule
import com.example.dimic.medicaltest.di.scope.ViewScope
import com.example.dimic.medicaltest.presentation.presenter.TestPresenter
import dagger.Subcomponent

@Subcomponent(modules = [TestModule::class])
@ViewScope
interface TestComponent {

    fun testPresenter(): TestPresenter
}