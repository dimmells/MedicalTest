package com.example.dimic.medicaltest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.dimic.medicaltest.R
import com.example.dimic.medicaltest.di.ApplicationLoader
import com.example.dimic.medicaltest.di.module.RegistrationModule
import com.example.dimic.medicaltest.presentation.presenter.RegistrationPresenter
import com.example.dimic.medicaltest.presentation.view.RegistrationView
import kotlinx.android.synthetic.main.fragment_register.*

class RegistrationFragment: BaseFragment(), RegistrationView {

    companion object {
        fun newInstance(): RegistrationFragment = RegistrationFragment()
    }

    @InjectPresenter
    lateinit var registrationPresenter: RegistrationPresenter

    @ProvidePresenter
    fun providePresenter(): RegistrationPresenter = ApplicationLoader.applicationComponent
            .registrationComponent(RegistrationModule())
            .registrationPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_register, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_login_enter.setOnClickListener {
            if (text_edit_registration_email.text.isNullOrEmpty() ||
                    text_edit_login_login.text.isNullOrEmpty() ||
                    text_edit_login_password.text.isNullOrEmpty())
                showToast(getString(R.string.error_fill_all_fields))
            else {
                registrationPresenter.register(
                        text_edit_registration_email.text.toString(),
                        text_edit_login_login.text.toString(),
                        text_edit_login_password.text.toString(),
                        1)
            }
        }
    }
}