package com.example.githubapp.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface GitHubClient {
    @Headers("Access: application/json")
    @POST("login/oauth/acess_token")
    @FormUrlEncoded
    fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String
    ): Call<AccessToken>

    @POST("login/oauth/authorize")
    @FormUrlEncoded
    fun autorisation(clientId: String): Call<AccessToken>

}