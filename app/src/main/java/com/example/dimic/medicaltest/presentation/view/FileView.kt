package com.example.dimic.medicaltest.presentation.view

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface FileView: BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun notifyMenuItemsChanged()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToTest(code: Int, count: Int, start: Int)

}