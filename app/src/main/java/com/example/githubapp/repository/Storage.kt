package com.example.githubapp.repository

import androidx.lifecycle.LiveData
import com.example.githubapp.api.AccessToken

interface Storage {
    fun saveToken(token: AccessToken)
    fun getToken(): AccessToken?
}