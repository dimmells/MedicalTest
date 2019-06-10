package com.example.dimic.medicaltest.ui.fragment

import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.example.dimic.medicaltest.presentation.view.BaseMvpView
import com.example.dimic.medicaltest.ui.navigation.BaseRouter

abstract class BaseFragment : MvpAppCompatFragment(), BaseMvpView {

    protected val router: BaseRouter
        get() = (parentFragment ?: activity) as BaseRouter

    fun showToast(text: String) {
        context?.let { Toast.makeText(it, text, Toast.LENGTH_LONG).show() }
    }

    fun showToast(stringResId: Int) {
        context?.getString(stringResId)?.let { showToast(it) }
    }
}