package com.example.githubapp.ui.repos

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.githubapp.repository.GithubRepository
import kotlinx.coroutines.flow.debounce
import javax.inject.Inject

class RepoListViewModel @Inject constructor(private val repository: GithubRepository) : ViewModel() {

    private val _currentRequest = MutableLiveData<String>(REPOS_ALL)

    val data = _currentRequest
        .switchMap {
            repository
                .getSearchResultStream(it)
                .cachedIn(viewModelScope)
                .debounce(200)
                .asLiveData()
        }

    fun searchRepo(request: String) {
        _currentRequest.postValue(request)
    }
}

