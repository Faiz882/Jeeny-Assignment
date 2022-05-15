package com.assignment.jeeny.repository

import androidx.lifecycle.LiveData
import com.assignment.jeeny.model.GithubRepoModel
import com.assignment.jeeny.model.GithubSearchSearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface IMainRepo {
    suspend fun searchRepo(search: String): Flow<GithubSearchSearchResponse>

    fun getSavedSearch() : LiveData<List<GithubRepoModel>>

    suspend fun saveSearch(githubRepoModel: GithubRepoModel)
}