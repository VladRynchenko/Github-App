package com.example.githubapp.repository

import android.service.autofill.UserData
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.githubapp.api.AccessToken
import com.example.githubapp.api.GitHubApi
import com.example.githubapp.di.LoginApi
import com.example.githubapp.di.Storage
import com.example.githubapp.login.CLIENT_ID
import com.example.githubapp.login.CLIENT_SECRET
import com.example.githubapp.login.LoginViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

class LoginRepository @Inject constructor(
    @LoginApi val retrofit: Retrofit,
    private val storage: Storage
) {


    fun getAccessToken(code: String): Observable<AccessToken> {
        val client = retrofit.create(GitHubApi::class.java)
        return client.getToken(CLIENT_ID, CLIENT_SECRET, code)
    }

    fun saveToken(token: AccessToken) {
        storage.saveToken(token)
    }

    fun getToken(): AccessToken? {
        return storage.getToken()
    }


}