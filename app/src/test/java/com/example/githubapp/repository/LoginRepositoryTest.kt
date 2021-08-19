package com.example.githubapp.repository

import com.example.githubapp.api.AccessToken
import com.example.githubapp.api.GitHubAuthApi
import io.reactivex.rxjava3.core.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginRepositoryTest {

    lateinit var repository: LoginRepository

    @Mock
    lateinit var api: GitHubAuthApi

    @Mock
    lateinit var storage: Storage

    private val token = AccessToken("token", "tokenType")


    @Before
    fun setUp() {
        repository = LoginRepository(api, storage)
        Mockito.`when`(repository.getToken()).thenReturn(token)
        Mockito.`when`(repository.getAccessToken("code")).thenReturn(Observable.just(token))
    }


    @Test
    fun getAccessToken() {
        val result = repository.getAccessToken("code")
        result.test().assertValue(token)
    }

    @Test
    fun successSaveAndTakeToken() {
        repository.saveToken(token)
        assertEquals(token, repository.getToken())
    }


}