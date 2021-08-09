package com.example.githubapp.api

import com.example.githubapp.models.Repos
import com.example.githubapp.models.SearchResponse
import com.example.githubapp.models.UserData
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {

    @GET("user")
    fun getUserProfile(): Observable<UserData>

    @GET("user/starred")
    fun getStarred(): Observable<List<Repos>>

    @GET("user/repos")
    fun getRepos(): Single<List<Repos>>

    @GET("search/repositories")
    fun searchRepos(
        @Query("q") query: String,
        @Query("per_page") per_page: Int,
        @Query("page") page: Int
    ): Single<SearchResponse>
}