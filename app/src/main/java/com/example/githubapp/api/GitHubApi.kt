package com.example.githubapp.api

import com.example.githubapp.models.Repos
import com.example.githubapp.models.UserData
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.*

interface GitHubApi {

    @GET("user")
    fun getUserProfile(): Observable<UserData>

    @GET("user/starred")
    fun getStarred(): Observable<List<Repos>>

    @GET("user/repos")
    fun getRepos(): Single<List<Repos>>
}