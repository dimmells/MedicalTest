package com.example.dimic.medicaltest.presentation.view

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface CreateExamView: BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setSpecialitySpinnerList(array: Array<String?>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setCourseSpinnerList(array: Array<String?>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setSubjectSpinnerList(array: Array<String?>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setFileSpinnerList(array: Array<String?>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(text: String)
}