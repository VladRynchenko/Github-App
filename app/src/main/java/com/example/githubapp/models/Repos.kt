package com.example.githubapp.models

data class Repos(
    override val full_name: String,
    val id: Long,
    val name: String,
    val description: String?,
    val url: String,
    val stars: Int,
    val forks: Int,
    val language: String?
) : DataItem()

data class NoResult(override val full_name: String = "NO_RESULT") : DataItem()
sealed class DataItem {
    abstract val full_name: String
}
