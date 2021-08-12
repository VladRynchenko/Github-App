package com.example.githubapp.di

import com.example.githubapp.auth.LoginComponent
import dagger.Module

@Module(subcomponents = [LoginComponent::class, UserComponent::class])
class AppSubcomponent {
}