package com.example.githubapp.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubapp.RxSchedulerRule
import com.example.githubapp.api.GitHubApi
import com.example.githubapp.models.Repos
import com.example.githubapp.models.UserData
import com.example.githubapp.profile.UserManager
import com.example.githubapp.repository.ProfileRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TestWatcher
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class RepoViewModelTest {

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: RepoViewModel
    private lateinit var repository: ProfileRepository

    private val userData =
        UserData("login", null, null, 0, null, null, null, null, null, null, null, null)
    private val repo = Repos("repo", 0, "repo", null, "url", userData, 0, 0, null)


    @Before
    fun setUp() {
        repository = Mockito.mock(ProfileRepository::class.java)
        viewModel = RepoViewModel(repository)
        `when`(repository.getRepo("login", "repo")).thenReturn(Single.just(repo))
        `when`(repository.getRepo("login", "error")).thenReturn(Single.error(Throwable("HTTP 401")))
    }

    @Test
    fun testGetRepo() {
        viewModel.getRepo("login", "repo")
        val value = viewModel.repo.value
        assertEquals(repo, value)
    }

    @Test
    fun testGetRepoWhenError() {
        viewModel.getRepo("login", "error")
        val value = viewModel.repo.value
        assertNotNull(value)
    }
}