package com.example.githubapp.auth

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubapp.RxSchedulerRule
import com.example.githubapp.api.AccessToken
import com.example.githubapp.getOrAwaitValue
import com.example.githubapp.repository.LoginRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify

class LoginViewModelTest {

    lateinit var repository: LoginRepository
    private lateinit var viewModel: LoginViewModel
    private val token = AccessToken("token", "tokenType")

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        repository = Mockito.mock(LoginRepository::class.java)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        viewModel = LoginViewModel(repository)
        Mockito.`when`(repository.getAccessToken("code")).thenReturn(Observable.just(token))
        Mockito.`when`(repository.getToken()).thenReturn(token)
    }

    @Test
    fun getAccessToken() {
        viewModel.getAccessToken("code")
        verify(repository).saveToken(token)
        verify(repository).getToken()
    }

    @Test
    fun getSavedToken() {
        viewModel.getSavedToken()
        verify(repository).getToken()
        val value = viewModel.token.getOrAwaitValue()
        assertEquals(token, value)
    }
}