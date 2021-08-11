package com.example.githubapp.profile

import com.example.githubapp.models.UserData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserManager @Inject constructor() {
    var userData: UserData? = null
}