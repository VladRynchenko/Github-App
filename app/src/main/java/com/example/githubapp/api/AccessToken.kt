package com.example.githubapp.api

import com.google.gson.annotations.SerializedName

data class AccessToken(
    @SerializedName("access_token")
    private var accessToken: String,

    @SerializedName("token_type")
    private var tokenType: String
)

