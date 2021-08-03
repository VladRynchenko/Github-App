package com.example.githubapp.di

import com.example.githubapp.api.GitHubApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Qualifier
import javax.inject.Singleton

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class LoginApi

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class BaseApi


@Module
class RemoteModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().build()

    @LoginApi
    @Provides
    @Singleton
    fun provideRetrofitLogin(gson: Gson, okHttpClient: OkHttpClient): GitHubApi {
        return Retrofit.Builder()
            .baseUrl(Companion.BASE_URL_SITE)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build().create(GitHubApi::class.java)
    }

    @BaseApi
    @Provides
    @Singleton
    fun provideRetrofitBase(gson: Gson, okHttpClient: OkHttpClient): GitHubApi {
        return Retrofit.Builder()
            .baseUrl(Companion.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build().create(GitHubApi::class.java)
    }

    companion object {
        const val BASE_URL_SITE = "https://github.com"
        const val BASE_URL = "https://api.github.com"
    }
}