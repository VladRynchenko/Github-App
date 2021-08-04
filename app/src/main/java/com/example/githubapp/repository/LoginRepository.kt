package com.example.githubapp.repository

import android.util.Log
import com.example.githubapp.api.AccessToken
import com.example.githubapp.api.GitHubApi
import com.example.githubapp.api.GitHubAuthApi
import com.example.githubapp.di.LoginApi
import com.example.githubapp.login.CLIENT_ID
import com.example.githubapp.login.CLIENT_SECRET
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val retrofit: GitHubAuthApi,
    private val storage: Storage
) {

    fun getAccessToken(code: String): Observable<AccessToken> {
        return retrofit.getToken(CLIENT_ID, CLIENT_SECRET, code)
    }

    fun saveToken(token: AccessToken) {
        Log.e("Save token", token.accessToken)
        storage.saveToken(token)
    }

    fun getToken(): AccessToken? {
        Log.e("GetToken", storage.getToken()?.accessToken.toString())
        return storage.getToken()
    }


}