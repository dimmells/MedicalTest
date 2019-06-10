package com.example.dimic.medicaltest.di.module

import com.example.dimic.medicaltest.data.server.TestService
import com.example.dimic.medicaltest.di.scope.ViewScope
import com.example.dimic.medicaltest.interact.FileInteract
import com.example.dimic.medicaltest.presentation.presenter.FilePresenter
import dagger.Module
import dagger.Provides

@Module
class FileModule {

    @Provides
    @ViewScope
    fun provideFileInteract(testService: TestService): FileInteract = FileInteract(testService)

    @Provides
    @ViewScope
    fun provideFilePresenter(fileInteract: FileInteract): FilePresenter = FilePresenter(fileInteract)
}