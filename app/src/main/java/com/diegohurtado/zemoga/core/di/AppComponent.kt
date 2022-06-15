package com.diegohurtado.zemoga.core.di

import com.diegohurtado.zemoga.core.di.modules.AppModule
import com.diegohurtado.zemoga.core.di.modules.DatabaseModule
import com.diegohurtado.zemoga.core.di.modules.NetworkModule
import com.diegohurtado.zemoga.feature.detail.viewmodel.DetailsViewModel
import com.diegohurtado.zemoga.feature.list.viewmodel.ListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class, AppModule::class])
interface AppComponent {

    //ViewModels
    fun inject(instance: ListViewModel)
    fun inject(instance: DetailsViewModel)

}