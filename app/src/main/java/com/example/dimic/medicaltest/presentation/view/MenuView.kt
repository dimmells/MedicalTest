package com.example.dimic.medicaltest.presentation.view

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface MenuView: BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setUserNickName(name: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun disableTeacherButtons()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun restartActivity()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToPreparation(code: Int)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToExams()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToResults()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToCreateExam()
}