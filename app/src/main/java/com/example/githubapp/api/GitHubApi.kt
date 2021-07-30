package com.example.githubapp.api

import com.example.githubapp.models.Repos
import com.example.githubapp.models.UserData
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import retrofit2.Call
import retrofit2.http.*

interface GitHubApi {

    @GET("users/{user}")
    fun getUserProfile(@Path("user") user: String): Observable<UserData>

    @GET("users/{user}/starred")
    fun getStarred(@Path("user") user: String): Observable<ArrayList<Repos>>

}