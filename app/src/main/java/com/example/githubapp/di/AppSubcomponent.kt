package com.example.githubapp.di

import dagger.Module

@Module(subcomponents = [LoginComponent::class, UserComponent::class])
class AppSubcomponent {
}