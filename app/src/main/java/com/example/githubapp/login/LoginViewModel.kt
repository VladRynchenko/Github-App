package com.example.githubapp.login

import android.util.Log
import androidx.lifecycle.*
import com.example.githubapp.api.AccessToken
import com.example.githubapp.repository.LoginRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.disposables.DisposableContainer
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(val repository: LoginRepository) : ViewModel() {

    private var disposeBag = CompositeDisposable()
    private val _token = MutableLiveData<AccessToken?>()
    val token: LiveData<AccessToken?>
        get() = _token

    fun getAccessToken(code: String) {
        disposeBag.add(
            repository.getAccessToken(code).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({ token ->
                    repository.saveToken(token)
                    _token.postValue(token)
                }, { error ->
                    Log.e(LoginViewModel::class.java.simpleName, error.message.toString())
                })
        )
    }

    fun getSavedToken(): AccessToken? {
        return repository.getToken()
    }

    override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
    }
}