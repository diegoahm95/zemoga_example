package com.diegohurtado.zemoga.core

import android.app.Application
import com.diegohurtado.zemoga.core.di.AppComponent
import com.diegohurtado.zemoga.core.di.DaggerAppComponent
import com.diegohurtado.zemoga.core.di.modules.AppModule

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        private lateinit var appComponent: AppComponent
        fun getAppComponent(): AppComponent = appComponent
    }
}