package com.example.githubapp.api

import com.example.githubapp.models.Repos
import com.example.githubapp.models.UserData
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import retrofit2.Call
import retrofit2.http.*

interface GitHubApi {

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    fun getToken(
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("code") code: String
    ): Observable<AccessToken>

    @GET("users/{user}")
    fun getUserProfile(@Path("user") user: String): Observable<UserData>

    @GET("users/{user}/starred")
    fun getStarred(@Path("user") user: String): Observable<ArrayList<Repos>>

}