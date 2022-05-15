package com.assignment.jeeny.di

import android.content.Context
import androidx.room.Room
import com.assignment.jeeny.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomDbModule {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context) = Room
        .databaseBuilder(
            context, AppDatabase::class.java, "app-db"
        )
        .fallbackToDestructiveMigration()
        .build()

    fun provideGithubDao(db: AppDatabase) = db.githubRepoDao()
}