package com.example.githubapp.repos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githubapp.models.DataItem
import com.example.githubapp.models.Repos
import com.example.githubapp.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReposViewModel @Inject constructor(private val repository: GithubRepository) : ViewModel() {

    fun getRepos(query: String): Flow<PagingData<DataItem>> {
        val result = repository.getSearchResultStream(query).cachedIn(viewModelScope)
        return result
    }
}

