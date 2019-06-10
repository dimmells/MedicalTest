package com.example.dimic.medicaltest.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.example.dimic.medicaltest.data.user.UserEntity
import com.example.dimic.medicaltest.interact.MenuInteract
import com.example.dimic.medicaltest.presentation.view.MenuView

@InjectViewState
class MenuPresenter(private val menuInteract: MenuInteract): BaseMvpPresenter<MenuView>() {

    private lateinit var user: UserEntity

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        user = menuInteract.getUser()
        user.login?.let { viewState.setUserNickName(it) }
//        if (!user.isTeacher)
//            viewState.disableTeacherButtons()
    }

    fun onPowerClick() {
        user.token = null
        user.login = null
        menuInteract.updateUser(user)
        viewState.restartActivity()
    }

    fun onPreparationClick() { viewState.navigateToPreparation("${user.specCode}${user.course}".toInt()) }

    fun onExamClick() { viewState.navigateToExams() }

    fun onResultClick() { viewState.navigateToResults() }

    fun onCreateExamClick() { viewState.navigateToCreateExam() }
}