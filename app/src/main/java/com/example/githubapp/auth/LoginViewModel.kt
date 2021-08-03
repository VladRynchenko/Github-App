package com.example.githubapp.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.api.AccessToken
import com.example.githubapp.repository.LoginRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel() {

    fun getAccessToken(code: String) {
            repository.getAccessToken(code).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({ token ->
                    repository.saveToken(token)
                }, { error ->
                    Log.e(LoginViewModel::class.java.simpleName, error.message.toString())
                })
    }

    fun getSavedToken(): AccessToken? {
        return repository.getToken()
    }
}