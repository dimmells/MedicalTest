package com.example.dimic.medicaltest.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.example.dimic.medicaltest.exception.EmailAlreadyExistException
import com.example.dimic.medicaltest.exception.LoginAlreadyExistException
import com.example.dimic.medicaltest.exception.TestApiException
import com.example.dimic.medicaltest.interact.RegistrationInteract
import com.example.dimic.medicaltest.presentation.view.RegistrationView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

@InjectViewState
class RegistrationPresenter(private val registrationInteract: RegistrationInteract): BaseMvpPresenter<RegistrationView>() {

    fun register(email: String, login: String, password: String, specialities: Int) =
            registrationInteract.register(email, login, password, specialities)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onComplete = { viewState.showToast("Реєстрація успішна") },
                            onError = {
                                when (it) {
                                    is LoginAlreadyExistException -> viewState.showToast("Данний логін існує, створіть інший")
                                    is EmailAlreadyExistException -> viewState.showToast("Данний Email уже зареєстрованно")
                                    is TestApiException -> viewState.showToast("TEST EXCEPTION")
                                    else -> viewState.showToast("Помилка, спробуйте пізніше")
                                }
                            }
                    )
                    .addTo(disposables)
}