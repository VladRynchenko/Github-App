package com.example.githubapp.repos

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.githubapp.models.DataItem
import com.example.githubapp.models.Repos
import com.example.githubapp.repository.GithubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import retrofit2.http.Query
import javax.inject.Inject

class ReposViewModel @Inject constructor(private val repository: GithubRepository) : ViewModel() {

    var response: Flow<PagingData<DataItem>>? = null

    fun searchRepo(queryString: String) {
        response = repository.getSearchResultStream(queryString)
            .cachedIn(viewModelScope).distinctUntilChanged()
    }
}

