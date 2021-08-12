package com.example.githubapp.repository

import com.example.githubapp.api.GitHubApi
import com.example.githubapp.models.Repos
import com.example.githubapp.models.SearchResponse
import com.example.githubapp.models.UserData
import com.example.githubapp.profile.UserManager
import io.reactivex.rxjava3.core.Single
import okhttp3.internal.userAgent
import javax.inject.Inject
import javax.inject.Singleton

const val PER_PAGE = 100
const val STARTING_PAGE = 1
const val USER_QUALIFIER = " user:"

class ProfileRepository @Inject constructor(
    private val retrofit: GitHubApi,
    private val userManager: UserManager
) {


    fun getProfile(): Single<UserData> {
        return retrofit.getUserProfile().doOnSuccess {
            userManager.userData = it
        }
    }

    fun getStarredList(): Single<List<Repos>> {
        return retrofit.getStarred()
    }

    fun getRepo(owner: String, name: String): Single<Repos> {
        return retrofit.getRepo(owner, name)
    }
}