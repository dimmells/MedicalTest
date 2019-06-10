package com.example.dimic.medicaltest.di.component

import com.example.dimic.medicaltest.di.module.FileModule
import com.example.dimic.medicaltest.di.scope.ViewScope
import com.example.dimic.medicaltest.presentation.presenter.FilePresenter
import dagger.Subcomponent

@Subcomponent(modules = [FileModule::class])
@ViewScope
interface FileComponent {

    fun filePresenter(): FilePresenter
}