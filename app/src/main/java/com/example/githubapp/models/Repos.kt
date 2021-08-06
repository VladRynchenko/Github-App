package com.example.githubapp.models

import com.google.gson.annotations.SerializedName

data class Repos(
    val id: Long,
    val name: String,
    val full_name: String,
    val description: String?,
    val url: String,
    val stars: Int,
    val forks: Int,
    val language: String?
)
