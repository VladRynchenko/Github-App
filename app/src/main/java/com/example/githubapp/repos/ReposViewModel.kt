package com.example.githubapp.repos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.models.Repos
import com.example.githubapp.repository.ProfileRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ReposViewModel @Inject constructor(private val repository: ProfileRepository) : ViewModel() {

    private lateinit var list: List<Repos>
    private val _reposList = MutableLiveData<List<Repos>>()
    val reposList: LiveData<List<Repos>>
        get() = _reposList


    fun getRepos() {
        repository.getReposList().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe({ list ->
                _reposList.value = list
                this.list = list
            }, {
                Log.e(ReposViewModel::class.simpleName, it.message.toString())
            })
    }

    fun searchRepo(query: String) {
        if (query.isNotEmpty()) {
            val subList = ArrayList<Repos>()
            list.forEach {
                if (it.name.contains(query, true))
                    subList.add(it)
            }
            _reposList.value = subList
        } else {
            _reposList.value = list
        }
    }
}

