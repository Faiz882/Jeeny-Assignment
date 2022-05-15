package com.assignment.jeeny.base

import com.assignment.jeeny.network.GithubService
import javax.inject.Inject

class MainRepo @Inject constructor(
    private val githubService: GithubService
) {
    suspend fun searchRepo(search: String) {
        githubService.searchRepo()
    }
}