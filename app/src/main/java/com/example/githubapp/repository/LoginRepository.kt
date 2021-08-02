package com.example.githubapp.repository

import com.example.githubapp.api.AccessToken
import com.example.githubapp.api.GitHubApi
import com.example.githubapp.di.LoginApi
import com.example.githubapp.login.CLIENT_ID
import com.example.githubapp.login.CLIENT_SECRET
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import javax.inject.Inject

class LoginRepository @Inject constructor(@LoginApi val retrofit: GitHubApi) {

    fun getAccessToken(code: String): Observable<AccessToken> {
        return retrofit.getToken(CLIENT_ID, CLIENT_SECRET, code)
    }


}