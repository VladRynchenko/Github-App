package com.example.githubapp.di

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.githubapp.MainActivity
import com.example.githubapp.user.ProfileFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RemoteModule::class, ViewModelFactoryModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: ProfileFragment)
    fun inject(mainActivity: MainActivity)

}