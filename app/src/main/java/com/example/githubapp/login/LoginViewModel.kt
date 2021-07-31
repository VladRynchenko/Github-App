package com.example.githubapp.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.githubapp.api.GitHubApi
import com.example.githubapp.di.LoginApi
import com.example.githubapp.di.RemoteModule
import com.example.githubapp.repository.LoginRepository
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query
import java.net.URI
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel() {

    fun getAccessToken(code: String) {
        repository.getAccessToken(code).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({ token ->
                Log.d(LoginViewModel::class.java.simpleName, token.accessToken)
            }, { error ->
                Log.e(LoginViewModel::class.java.simpleName, error.message.toString())
            })

    }
}