package com.assignment.jeeny.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.assignment.jeeny.model.GithubRepoModel

@Dao
interface GithubRepoDao {
    @Query("SELECT * FROM GithubRepoModel")
    suspend fun getAll(): LiveData<List<GithubRepoDao>>

    @Insert
    suspend fun insertAll(vararg users: GithubRepoModel)
}