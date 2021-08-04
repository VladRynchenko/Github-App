package com.example.githubapp.api

import com.google.gson.annotations.SerializedName

data class AccessToken(
    @SerializedName("access_token")
    var accessToken: String,

    @SerializedName("token_type")
    var tokenType: String
)

