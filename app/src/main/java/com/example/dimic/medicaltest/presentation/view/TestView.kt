package com.example.dimic.medicaltest.presentation.view

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface TestView: BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun notifyMenuItemsChanged()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun notifyMenuItemChanged(position: Int)

}