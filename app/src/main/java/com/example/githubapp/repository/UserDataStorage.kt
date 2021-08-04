package com.example.githubapp.repository

import android.content.Context
import com.example.githubapp.api.AccessToken
import com.google.gson.Gson
import javax.inject.Inject

class UserDataStorage @Inject constructor(context: Context, val gson: Gson): Storage {
    private val sharedPreferences = context.getSharedPreferences("USER_TOKEN", Context.MODE_PRIVATE)

    override fun saveToken(token: AccessToken) {
        with(sharedPreferences.edit()) {
            putString("STATUS", gson.toJson(token))
            apply()
        }
    }

    override fun getToken(): AccessToken? {
        return gson.fromJson(sharedPreferences.getString("STATUS", ""), AccessToken::class.java)
    }

}
