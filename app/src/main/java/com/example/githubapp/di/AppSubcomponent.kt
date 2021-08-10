package com.example.githubapp.di

import com.example.githubapp.auth.LoginComponent
import com.example.githubapp.di.profile.UserComponent
import dagger.Module

@Module(subcomponents = [LoginComponent::class, UserComponent::class])
class AppSubcomponent {
}