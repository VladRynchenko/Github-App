package com.example.githubapp.repository

import com.example.githubapp.api.GitHubApi
import com.example.githubapp.models.Repos
import com.example.githubapp.models.SearchResponse
import com.example.githubapp.models.UserData
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

const val PER_PAGE = 100
const val PAGE = 1
const val USER_QUALIFIER = " user:"

@Singleton
class ProfileRepository @Inject constructor(private val retrofit: GitHubApi) {
    var userData: UserData? = null

    fun getProfile(): Single<UserData> {
        return retrofit.getUserProfile().doOnSuccess {
            userData = it
        }
    }

    fun getStarredList(): Single<List<Repos>> {
        return retrofit.getStarred()
    }

    fun searchRepos(query: String): Single<SearchResponse> {
        return retrofit.searchRepos(query + USER_QUALIFIER + userData?.login, PER_PAGE, PAGE)
    }
}