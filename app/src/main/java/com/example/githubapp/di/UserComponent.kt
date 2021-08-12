package com.example.githubapp.di

import android.content.Context
import com.example.githubapp.repos.ReposFragment
import com.example.githubapp.profile.ProfileFragment
import com.example.githubapp.repo.RepoFragment
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
    fun inject(fragment: RepoFragment)

}
