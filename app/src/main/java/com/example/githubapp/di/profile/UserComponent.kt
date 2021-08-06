package com.example.githubapp.di.profile

import android.content.Context
import com.example.githubapp.repos.ReposFragment
import com.example.githubapp.profile.ProfileFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent
interface UserComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): UserComponent
    }

    fun inject(fragment: ProfileFragment)
    fun inject(fragment: ReposFragment)

}
