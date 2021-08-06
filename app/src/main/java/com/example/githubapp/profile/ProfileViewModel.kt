package com.example.githubapp.profile

import android.util.Log
import androidx.lifecycle.*
import com.example.githubapp.models.UserData
import com.example.githubapp.repository.ProfileRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val repository: ProfileRepository) :
    ViewModel() {
    private val _userData = MutableLiveData<UserData>()
    val userData: LiveData<UserData>
        get() = _userData

    private val _userStars = MutableLiveData<Int>()
    val userStars: LiveData<Int>
        get() = _userStars

    private val _navigatingToLogin = MutableLiveData<Boolean>()
    val navigatingToLogin: LiveData<Boolean>
        get() = _navigatingToLogin


    fun getData() {
        getUserData()
        getStarCount()
    }

    private fun getUserData() {
        repository.getProfile().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _userData.value = it
            }, {
                Log.e(ProfileViewModel::class.java.simpleName, it.message.toString())
                _navigatingToLogin.value = true
            })
    }


    private fun getStarCount() {
        repository.getStarredList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                _userStars.value = list.size
            }, {
                Log.e(ProfileViewModel::class.java.simpleName, it.message.toString())
            })
    }
}