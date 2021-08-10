package com.example.githubapp.repos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.models.DataItem
import com.example.githubapp.models.NoResult
import com.example.githubapp.repository.ProfileRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ReposViewModel @Inject constructor(private val repository: ProfileRepository) : ViewModel() {

    private val _reposList = MutableLiveData<List<DataItem>>()
    val reposList: LiveData<List<DataItem>>
        get() = _reposList


    fun getRepos(query: String) {
        repository.searchRepos(query).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe({ list ->
                if (list.items.isNotEmpty())
                    _reposList.value = list.items
                else
                    _reposList.value = listOf(NoResult())

            }, {
                Log.e(ReposViewModel::class.simpleName, it.message.toString())
            })
    }
}

