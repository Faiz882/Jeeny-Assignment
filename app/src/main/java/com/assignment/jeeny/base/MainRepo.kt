package com.assignment.jeeny.base

import com.assignment.jeeny.model.GithubSearchSearchResponse
import com.assignment.jeeny.network.GithubService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MainRepo @Inject constructor(
    private val githubService: GithubService
) {
    suspend fun searchRepo(search: String): Flow<GithubSearchSearchResponse> {
        return githubService.searchRepo(search)
    }
}