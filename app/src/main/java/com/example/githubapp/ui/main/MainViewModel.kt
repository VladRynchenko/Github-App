package com.example.githubapp.ui.main

import androidx.lifecycle.ViewModel
import com.example.githubapp.repository.LoginRepository
import javax.inject.Inject


class MainViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel() {

    fun IsUserLogin() =
        repository.getToken() != null

}