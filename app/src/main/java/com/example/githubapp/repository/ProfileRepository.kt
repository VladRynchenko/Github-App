package com.example.githubapp.repository

import com.example.githubapp.api.GitHubApi
import com.example.githubapp.di.BaseApi
import com.example.githubapp.models.Repos
import com.example.githubapp.models.UserData
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val retrofit: GitHubApi) {

    fun getProfile(): Observable<UserData> {
        return retrofit.getUserProfile()
    }

    fun getStarredList(): Observable<List<Repos>> {
        return retrofit.getStarred()
    }

    fun getReposList(): Single<List<Repos>> {
        return retrofit.getRepos()
    }
}