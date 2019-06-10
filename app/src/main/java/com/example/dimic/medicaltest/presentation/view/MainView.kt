package com.example.dimic.medicaltest.presentation.view

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface MainView : BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToSpeciality()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToRegistration()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToLogin()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToMenu()
}