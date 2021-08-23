package com.example.githubapp.repository

import androidx.paging.PagingSource
import com.example.githubapp.api.GitHubApi
import com.example.githubapp.models.Repos
import com.example.githubapp.models.SearchResponse
import com.example.githubapp.models.UserData
import com.example.githubapp.ui.repos.GithubRepoDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@OptIn(ExperimentalCoroutinesApi::class)
class GithubRepositoryTest {

    lateinit var api: GitHubApi
    lateinit var userManager: UserManager
    private val userData = UserData("login", null, null, 0, null, null, null, null, null, null, null, null)
    private lateinit var repository: GithubRepository
    private val repo = Repos("repo", 0, "repo", null, "url", userData, 0, 0, null)
    private val repoList = listOf(repo, repo)
    private val response = SearchResponse(2, repoList)


    @Before
    fun setUp() {
        api = mock(GitHubApi::class.java)
        userManager = mock(UserManager::class.java)
        repository = GithubRepository(api, userManager)
        `when`(userManager.userData).thenReturn(userData)
    }


    @Test
    fun loadReturnsPageWhenOnSuccessfulLoadOfItemKeyedData() = runBlockingTest {
        `when`(api.searchRepos("query user:login", 2, 1)).thenReturn(response)
        val pagingSource = GithubRepoDataSource("query", api, userManager)
        assertEquals(
            PagingSource.LoadResult.Page(
                data = repoList,
                prevKey = null,
                nextKey = 1
            ),
            pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            )
        )
    }
}