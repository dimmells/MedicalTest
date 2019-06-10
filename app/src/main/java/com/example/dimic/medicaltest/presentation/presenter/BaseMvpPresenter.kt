package com.example.dimic.medicaltest.presentation.presenter

import com.arellomobile.mvp.MvpPresenter
import com.example.dimic.medicaltest.presentation.view.BaseMvpView
import io.reactivex.disposables.CompositeDisposable

abstract class BaseMvpPresenter<T : BaseMvpView> : MvpPresenter<T>() {

    protected val disposables = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}