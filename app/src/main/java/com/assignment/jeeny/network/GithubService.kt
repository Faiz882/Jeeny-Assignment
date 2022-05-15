package com.assignment.jeeny.network

import com.assignment.jeeny.model.GithubSearchSearchResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    @GET("search/repositories")
    suspend fun searchRepo(@Query("q") search: String, @Query("page") page: Int = 1): Flow<GithubSearchSearchResponse>
}