package com.example.githubapp.ui.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.models.Repos
import com.example.githubapp.repository.ProfileRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val repository: ProfileRepository) : ViewModel() {

    private val _repo = MutableLiveData<Repos>()
    val repo: LiveData<Repos>
        get() = _repo

    fun getRepo(owner: String, name: String) {
        repository.getRepo(owner, name).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                _repo.value = it
            }, {
                Log.e(DetailViewModel::class.simpleName, it.message.toString())
            })
    }


}
