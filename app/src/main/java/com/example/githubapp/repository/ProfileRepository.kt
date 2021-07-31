package com.example.githubapp.repository

import com.example.githubapp.api.GitHubApi
import com.example.githubapp.models.Repos
import com.example.githubapp.models.UserData
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import retrofit2.Retrofit
import javax.inject.Inject

class ProfileRepository @Inject constructor(var retrofit: Retrofit) {

    fun getProfile(userId: String): Observable<UserData> {
        val client = retrofit.create(GitHubApi::class.java)
        return client.getUserProfile(userId)
    }

    fun getStarredList(userId: String): Observable<ArrayList<Repos>> {
        val client = retrofit.create(GitHubApi::class.java)
        return client.getStarred(userId)
    }
}