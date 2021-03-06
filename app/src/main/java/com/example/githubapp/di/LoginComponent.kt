package com.example.githubapp.di

import android.content.Context
import com.example.githubapp.ui.auth.LoginFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent
interface LoginComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): LoginComponent
    }

    fun inject(fragment: LoginFragment)
}