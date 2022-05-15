package com.assignment.jeeny.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.jeeny.base.BaseViewModel
import com.assignment.jeeny.repository.MainRepo
import com.assignment.jeeny.model.GithubRepoModel
import com.assignment.jeeny.model.GithubSearchSearchResponse
import com.assignment.jeeny.repository.IMainRepo
import com.assignment.jeeny.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: IMainRepo
) : BaseViewModel() {
    private val _githubSearchResponse = MutableLiveData<Resource<GithubSearchSearchResponse>>()
    val githubSearchResponse: LiveData<Resource<GithubSearchSearchResponse>> = _githubSearchResponse

    fun searchRepo(search: String) = viewModelScope.launch {
        repo.searchRepo(search)
            .catch {
                _githubSearchResponse.postValue(Resource.error(data = null, this.toString()))
            }
            .onStart {
                _githubSearchResponse.postValue(Resource.loading(data = null))
            }
            .collect { _githubSearchResponse.postValue(Resource.success(data = it)) }

    }

    fun getSavedSearch() = repo.getSavedSearch()

    fun saveSearch(githubRepoModel: GithubRepoModel) {
        viewModelScope.launch { repo.saveSearch(githubRepoModel) }
    }
}