package com.example.githubapp.repos

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githubapp.models.DataItem
import com.example.githubapp.models.Repos
import com.example.githubapp.repository.GithubRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ReposViewModel @Inject constructor(private val repository: GithubRepository) : ViewModel() {

    private val _repos = MutableLiveData<Flow<PagingData<DataItem>>>()
    val repos: LiveData<Flow<PagingData<DataItem>>>
        get() = _repos

    private var searchJob: Job? = null

    fun getRepos(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _repos.value = repository.getSearchResultStream(query).cachedIn(viewModelScope)
        }
    }
}

