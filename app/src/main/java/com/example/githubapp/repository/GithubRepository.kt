package com.example.githubapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.githubapp.api.GitHubApi
import com.example.githubapp.models.DataItem
import com.example.githubapp.ui.repos.GithubRepoDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val service: GitHubApi,
    private val userManager: UserManager
) {

    fun getSearchResultStream(query: String): Flow<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GithubRepoDataSource(query, service, userManager) }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 50
    }
}
