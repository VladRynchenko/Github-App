package com.example.githubapp.repos

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubapp.api.GitHubApi
import com.example.githubapp.models.DataItem
import com.example.githubapp.models.NoResult
import com.example.githubapp.profile.UserManager
import com.example.githubapp.repository.STARTING_PAGE
import com.example.githubapp.repository.USER_QUALIFIER
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

private const val NETWORK_PAGE_SIZE = 3

class GithubRepoDataSource(
    private val query: String,
    private val service: GitHubApi,
    private val userManager: UserManager
) :
    PagingSource<Int, DataItem>() {
    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        val position = params.key ?: STARTING_PAGE
        val apiQuery = query + USER_QUALIFIER + userManager.userData?.login
        return try {
            delay(200)
            val response = service.searchRepos(apiQuery, params.loadSize, position)
            val repos: List<DataItem> =
                if (response.total_count > 0) response.items else listOf(NoResult())
            val nextKey = if (response.items.isEmpty())
                null
            else
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            LoadResult.Page(
                data = repos,
                prevKey = if (position == STARTING_PAGE) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}