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

class ProfileRepository @Inject constructor(val retrofit: GitHubApi) {

    fun getProfile(userId: String): Observable<UserData> {
        return retrofit.getUserProfile(userId)
    }

    fun getStarredList(userId: String): Observable<ArrayList<Repos>> {
        return retrofit.getStarred(userId)
    }
}