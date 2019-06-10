package com.example.dimic.medicaltest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.dimic.medicaltest.R
import com.example.dimic.medicaltest.di.ApplicationLoader
import com.example.dimic.medicaltest.di.module.LoginModule
import com.example.dimic.medicaltest.presentation.presenter.LoginPresenter
import com.example.dimic.medicaltest.presentation.view.LoginView
import com.example.dimic.medicaltest.ui.navigation.MenuRouter
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment: BaseFragment(), LoginView {

    companion object {
        fun newInstance(): LoginFragment = LoginFragment()
    }

    @InjectPresenter
    lateinit var loginPresenter: LoginPresenter

    @ProvidePresenter
    fun providePresenter(): LoginPresenter = ApplicationLoader.applicationComponent
            .loginComponent(LoginModule())
            .loginPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text_view_login_registration.setOnClickListener { loginPresenter.onRegisterClick() }
        button_login_enter.setOnClickListener { onEnterClick() }
    }

    private fun onEnterClick() {
        val login = text_edit_login_login.text.toString()
        val password = text_edit_login_password.text.toString()
        if (login.isNotBlank() && password.isNotBlank()) {
            loginPresenter.login(login, password)
        } else {
            showToast("Заповніть всі поля")
        }
    }

    override fun navigateToRegistration() = (router as MenuRouter).navigateToRegistration()

    override fun navigateToMenu() = (router as MenuRouter).navigateToMenu()
}