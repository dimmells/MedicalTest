package com.example.dimic.medicaltest.di

import android.support.multidex.MultiDexApplication
import com.example.dimic.medicaltest.di.component.ApplicationComponent
import com.example.dimic.medicaltest.di.component.DaggerApplicationComponent
import com.example.dimic.medicaltest.di.module.ApplicationModule
import com.example.dimic.medicaltest.rx.RxErrorHandler
import io.reactivex.plugins.RxJavaPlugins

class ApplicationLoader : MultiDexApplication() {

    companion object {

        init {
            RxJavaPlugins.setErrorHandler(RxErrorHandler())
        }

        private lateinit var application: ApplicationLoader

        val applicationComponent
            get() = application.applicationComponent

    }

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        application = this
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}