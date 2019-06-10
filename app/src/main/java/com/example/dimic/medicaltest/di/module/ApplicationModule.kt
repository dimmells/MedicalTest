package com.example.dimic.medicaltest.di.module

import android.content.Context
import com.example.dimic.medicaltest.di.ApplicationLoader
import com.example.dimic.medicaltest.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: ApplicationLoader) {

    @Provides
    @ApplicationScope
    fun provideContext(): Context = application

}