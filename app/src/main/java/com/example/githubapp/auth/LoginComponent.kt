package com.example.githubapp.auth

import android.content.Context
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