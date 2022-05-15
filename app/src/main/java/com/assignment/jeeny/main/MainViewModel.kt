package com.assignment.jeeny.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.jeeny.base.MainRepo
import com.assignment.jeeny.model.GithubRepoModel
import com.assignment.jeeny.model.GithubSearchSearchResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: MainRepo
) : ViewModel() {
    private val _githubSearchResponse = MutableLiveData<GithubSearchSearchResponse>()
    val githubSearchResponse: LiveData<GithubSearchSearchResponse> = _githubSearchResponse

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error


    fun searchRepo(search: String) = viewModelScope.launch {
        repo.searchRepo(search)
            .catch {
                _error.postValue(this.toString())
                _error.postValue(null)
            }
            .collect { _githubSearchResponse.postValue(it) }
    }

    fun getSavedSearch() = repo.getSavedSearch()

    fun saveSearch(githubRepoModel: GithubRepoModel) {
        viewModelScope.launch { repo.saveSearch(githubRepoModel) }
    }
}