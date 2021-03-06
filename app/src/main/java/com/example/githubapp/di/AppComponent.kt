package com.example.githubapp.di

import android.content.Context
import com.example.githubapp.ui.main.MainActivity
import com.example.githubapp.repository.StorageModule
import com.example.githubapp.viewmodelsfactory.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RemoteModule::class, StorageModule::class, ViewModelFactoryModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun loginComponent(): LoginComponent.Factory
    fun userComponent(): UserComponent.Factory
    fun inject(activity: MainActivity)

}