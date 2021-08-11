package com.example.githubapp.repos

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githubapp.models.DataItem
import com.example.githubapp.repository.GithubRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

class ReposViewModel @Inject constructor(private val repository: GithubRepository) : ViewModel() {

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

