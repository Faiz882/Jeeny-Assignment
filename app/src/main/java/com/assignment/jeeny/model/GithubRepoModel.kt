package com.assignment.jeeny.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class GithubRepoModel(
    @PrimaryKey
    val id: Int,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("forks_count")
    val forks_Count: Int?,
    @SerializedName("stargazers_count")
    val stargazersCount: Int?,
    @SerializedName("open_issues_count")
    val openIssuesCount: Int?
)

data class GithubSearchSearchResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    val items: List<GithubRepoModel>
)