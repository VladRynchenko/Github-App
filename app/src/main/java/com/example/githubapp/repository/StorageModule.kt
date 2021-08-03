package com.example.githubapp.repository

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides

@Module
class StorageModule {

    @Provides
    fun storageLoginProvide(context: Context, gson: Gson): Storage {
        return UserDataStorage(context, gson)
    }
}