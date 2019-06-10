package com.example.dimic.medicaltest.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.example.dimic.medicaltest.interact.MainInteract
import com.example.dimic.medicaltest.presentation.view.MainView

@InjectViewState
class MainPresenter(private val mainInteract: MainInteract) : BaseMvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        if (mainInteract.isUserAuthorized())
            viewState.navigateToMenu()
        else
            viewState.navigateToLogin()
    }

}