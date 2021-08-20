package com.example.githubapp.auth

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubapp.RxSchedulerRule
import com.example.githubapp.api.AccessToken
import com.example.githubapp.repository.LoginRepository
import io.reactivex.rxjava3.core.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.Mockito.verify

class LoginViewModelTest {

    lateinit var repository: LoginRepository
    private lateinit var viewModel: LoginViewModel
    private val token = AccessToken("token", "tokenType")
    private val emptyToken = AccessToken("null", "null")

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        repository = Mockito.mock(LoginRepository::class.java)
        viewModel = LoginViewModel(repository)
        Mockito.`when`(repository.getAccessToken("code")).thenReturn(Observable.just(token))
        Mockito.`when`(repository.getAccessToken("error"))
            .thenReturn(Observable.error(Throwable("HTTP 401")))
        Mockito.`when`(repository.getToken()).thenReturn(token)
    }

    @Test
    fun getAccessToken() {
        viewModel.getAccessToken("code")
        val value = viewModel.token.value
        assertEquals(token, value)
    }

    @Test
    fun getSavedToken() {
        viewModel.getSavedToken()
        verify(repository).getToken()
        val value = viewModel.token.value
        assertEquals(token, value)
    }

    @Test
    fun checkNotSaveWhenErrorAccessToken() {
        viewModel.getAccessToken("error")
        verify(repository, never()).saveToken(emptyToken)
    }
}