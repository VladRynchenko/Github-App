package com.example.githubapp.api

import com.example.githubapp.models.Repos
import com.example.githubapp.models.SearchResponse
import com.example.githubapp.models.UserData
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {

    @GET("user")
    fun getUserProfile(): Single<UserData>

    @GET("user/starred")
    fun getStarred(): Single<List<Repos>>

    @GET("repos/{owner}/{name}")
    fun getRepo(
        @Path("owner") owner: String,
        @Path("name") name: String
    ): Single<Repos>

    @GET("search/repositories")
    suspend fun searchRepos(
        @Query("q") query: String,
        @Query("per_page") per_page: Int,
        @Query("page") page: Int
    ): SearchResponse


}