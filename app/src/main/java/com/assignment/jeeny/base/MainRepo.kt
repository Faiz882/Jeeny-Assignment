package com.assignment.jeeny.base

import com.assignment.jeeny.db.GithubRepoDao
import com.assignment.jeeny.model.GithubRepoModel
import com.assignment.jeeny.model.GithubSearchSearchResponse
import com.assignment.jeeny.network.GithubService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MainRepo @Inject constructor(
    private val githubService: GithubService,
    private val githubRepoDao: GithubRepoDao
) {
    suspend fun searchRepo(search: String): Flow<GithubSearchSearchResponse> {
        return flow {
            emit(githubService.searchRepo(search))
        }.flowOn(Dispatchers.IO)
    }

    fun getSavedSearch() = githubRepoDao.getAll()

    suspend fun saveSearch(githubRepoModel: GithubRepoModel) {
        githubRepoDao.insertAll(githubRepoModel)
    }
}