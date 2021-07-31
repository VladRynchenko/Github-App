package com.example.githubapp.api

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import retrofit2.Call
import retrofit2.http.*

const val authPath = "https://github.com/login/oauth/authorize/"

interface GitHubApi {

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    fun getToken(
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("code") code: String
    ): Observable<AccessToken>
}