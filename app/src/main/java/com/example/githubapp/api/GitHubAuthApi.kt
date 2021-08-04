package com.example.githubapp.api

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface GitHubAuthApi {
    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    fun getToken(
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("code") code: String
    ): Observable<AccessToken>
}