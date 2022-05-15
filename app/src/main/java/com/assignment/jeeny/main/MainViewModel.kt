package com.assignment.jeeny.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.jeeny.base.MainRepo
import com.assignment.jeeny.model.GithubRepoModel
import com.assignment.jeeny.model.GithubSearchSearchResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: MainRepo
) : ViewModel() {
    private val _githubSearchResponse = MutableLiveData<GithubSearchSearchResponse>()
    val githubSearchResponse: LiveData<GithubSearchSearchResponse> = _githubSearchResponse

    fun searchRepo(search: String) = viewModelScope.launch {
        repo.searchRepo(search).collect {
            _githubSearchResponse.postValue(it)
        }
    }

    fun getSavedSearch() = repo.getSavedSearch()

    fun saveSearch(githubRepoModel: GithubRepoModel) {
        viewModelScope.launch { repo.saveSearch(githubRepoModel) }
    }
}