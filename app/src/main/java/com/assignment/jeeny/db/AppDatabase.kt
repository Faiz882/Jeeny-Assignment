package com.assignment.jeeny.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.assignment.jeeny.model.GithubRepoModel

@Database(entities = [GithubRepoModel::class], version = 1)
@TypeConverters(OwnerTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun githubRepoDao(): GithubRepoDao
}