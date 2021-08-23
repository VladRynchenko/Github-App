package com.example.githubapp.di

import android.content.Context
import com.example.githubapp.ui.repos.ReposFragment
import com.example.githubapp.ui.profile.ProfileFragment
import com.example.githubapp.ui.repo.DetailFragment
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
    fun inject(fragment: DetailFragment)

}
