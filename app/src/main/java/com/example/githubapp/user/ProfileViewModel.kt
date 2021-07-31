package com.example.githubapp.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.models.UserData
import com.example.githubapp.repository.ProfileRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ProfileViewModel @Inject constructor(var repository: ProfileRepository) : ViewModel() {
    private val _userData = MutableLiveData<UserData>()
    val userData: LiveData<UserData>
        get() = _userData


    fun getData(userName: String) {
        getUserData(userName)
        getStarCount(userName)
    }

    fun getUserData(userName: String) {
        repository.getProfile(userName).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _userData.value = it
                return@subscribe
            }, {
                Log.e(ProfileViewModel::class.java.simpleName, it.message.toString())
            })
    }

    fun getStarCount(userName: String) {
        repository.getStarredList(userName).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _userData.value?.star = it.size
                _userData.postValue(_userData.value)
                Log.d(ProfileViewModel::class.java.simpleName, it.size.toString())
                return@subscribe
            }, {
                Log.e(ProfileViewModel::class.java.simpleName, it.message.toString())
            })
    }
}