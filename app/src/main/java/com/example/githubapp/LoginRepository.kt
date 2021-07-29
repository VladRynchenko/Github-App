package com.example.githubapp

import com.example.githubapp.api.GitHubClient
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Inject


@Module
class LoginRepository {

    @Provides
    fun login(retrofit: Retrofit, login: String, password: String) {
        val client = retrofit.create(GitHubClient::class.java)

    }
}