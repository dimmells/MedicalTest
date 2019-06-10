package com.example.dimic.medicaltest.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.example.dimic.medicaltest.interact.LoginInteract
import com.example.dimic.medicaltest.presentation.view.LoginView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

@InjectViewState
class LoginPresenter(private val loginInteract: LoginInteract): BaseMvpPresenter<LoginView>() {

    fun onRegisterClick() = viewState.navigateToRegistration()

    fun login(login: String, password: String) =
        loginInteract.login(login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onComplete = {
                            val user = loginInteract.getUser()
                            if (user.token.isNullOrEmpty()) {
                                viewState.showToast("Помилка, спробуйте пізніше")
                            } else {
                                viewState.showToast("Вітаємо ${user.login}")
                                viewState.navigateToMenu()
                            }
                        },
                        onError = { viewState.showToast("Помилка, спробуйте пізніше") }
                )
                .addTo(disposables)
}