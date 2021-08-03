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


    fun getData(userName: String) {
        getUserData(userName)
        getStarCount(userName)
    }

    fun getUserData(userName: String) {
        repository.getProfile(userName).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _userData.value = it
            }, {
                Log.e(ProfileViewModel::class.java.simpleName, it.message.toString())
            })
    }

    fun getStarCount(userName: String) {
        repository.getStarredList(userName).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ listStar ->
                _userStars.value = listStar.size
            }, {
                Log.e(ProfileViewModel::class.java.simpleName, it.message.toString())
            })
    }
}