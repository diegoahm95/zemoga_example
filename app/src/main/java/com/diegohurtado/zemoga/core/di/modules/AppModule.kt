package com.diegohurtado.zemoga.core.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {
    @Provides
    @Singleton
    fun providesApplication(): Application = app

    @Provides
    @Singleton
    fun providesApplicationContext(): Context = app
}