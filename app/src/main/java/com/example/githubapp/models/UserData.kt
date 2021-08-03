package com.example.githubapp.models

data class UserData(
    val login: String,
    val avatar_url: String?,
    val name: String?,
    var star: Int = 0,
    val company: String?,
    val blog: String?,
    val location: String?,
    val email: String?,
    val bio: String?,
    val twitter_username: String?,
    val public_repos: Int?,
    val public_gists: Int?,
    val followers: Int = 0,
    val following: Int = 0
) {

}