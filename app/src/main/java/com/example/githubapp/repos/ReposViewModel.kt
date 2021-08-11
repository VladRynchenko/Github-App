package com.example.githubapp.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.githubapp.models.DataItem
import com.example.githubapp.repository.GithubRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class ReposViewModel @Inject constructor(private val repository: GithubRepository) : ViewModel() {

    private val _repos = MutableLiveData<PagingData<DataItem>>()
    val repos: LiveData<PagingData<DataItem>>
        get() = _repos

    fun searchRepo(query: String) {
        viewModelScope.launch {
            repository.getSearchResultStream(query).collectLatest {
                _repos.value = it
            }
        }
    }
}

