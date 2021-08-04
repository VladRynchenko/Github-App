package com.example.githubapp.di

import android.content.Context
import com.example.githubapp.main.MainActivity
import com.example.githubapp.auth.LoginComponent
import com.example.githubapp.profile.ProfileFragment
import com.example.githubapp.repository.StorageModule
import com.example.githubapp.viewmodels.ViewModelFactoryModule
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
    fun inject(fragment: ProfileFragment)
    fun inject(activity: MainActivity)

}