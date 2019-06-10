package com.example.dimic.medicaltest.presentation.view

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface SubjectView: BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun notifyMenuItemsChanged()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToFile(subjectCode: Int)

}