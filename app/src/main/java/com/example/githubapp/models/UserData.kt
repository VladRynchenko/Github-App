package com.example.githubapp.models

data class UserData(
    val login: String,
    var avatar_url: String?,
    var name: String?,
    var star: Int = 0,
    var company: String?,
    var blog: String?,
    var location: String?,
    var email: String?,
    var bio: String?,
    var twitter_username: String?,
    var public_repos: Int?,
    var public_gists: Int?,
    var followers: Int = 0,
    var following: Int = 0
) {

}