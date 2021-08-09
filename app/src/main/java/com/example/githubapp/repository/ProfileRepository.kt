package com.example.githubapp.repository

import com.example.githubapp.api.GitHubApi
import com.example.githubapp.di.BaseApi
import com.example.githubapp.models.Repos
import com.example.githubapp.models.SearchResponse
import com.example.githubapp.models.UserData
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

const val PER_PAGE = 100
const val PAGE = 1
const val USER_QUALIFIER = " user:"

@Singleton
class ProfileRepository @Inject constructor(private val retrofit: GitHubApi) {
    var userData: UserData? = null

    fun getProfile(): Observable<UserData> {
        return retrofit.getUserProfile()
    }

    fun getStarredList(): Observable<List<Repos>> {
        return retrofit.getStarred()
    }

    fun searchRepos(query: String): Single<SearchResponse> {
        return retrofit.searchRepos(query + USER_QUALIFIER + userData?.login, PER_PAGE, PAGE)
    }
}