package com.example.githubapp.models

data class SearchResponse(
    var total_count: Int,
    var items: List<Repos>
)
