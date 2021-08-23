package com.example.githubapp.repository

import com.example.githubapp.RxSchedulerRule
import com.example.githubapp.api.GitHubApi
import com.example.githubapp.models.Repos
import com.example.githubapp.models.UserData
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class ProfileRepositoryTest {

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    lateinit var repository: ProfileRepository
    lateinit var api: GitHubApi
    lateinit var userManager: UserManager
    private val userData =
        UserData("login", null, null, 0, null, null, null, null, null, null, null, null)
    private val repo = Repos("repo", 0, "repo", null, "url", userData, 0, 0, null)

    @Before
    fun setUp() {
        api = Mockito.mock(GitHubApi::class.java)
        userManager = Mockito.mock(UserManager::class.java)
        `when`(api.getUserProfile()).thenReturn(Single.just(userData))
        `when`(api.getStarred()).thenReturn(Single.just(listOf(repo)))
        `when`(api.getRepo("login", "repo")).thenReturn(Single.just(repo))
        repository = ProfileRepository(api, userManager)
    }

    @Test
    fun getProfile() {
        repository.getProfile().test().assertValue(userData)
    }

    @Test
    fun getStarredList() {
        repository.getStarredList().test().assertValue(listOf(repo))
    }

    @Test
    fun getRepo() {
        repository.getRepo("login", "repo").test().assertValue(repo)
    }
}