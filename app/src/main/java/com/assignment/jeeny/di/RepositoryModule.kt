package com.assignment.jeeny.di

import com.assignment.jeeny.repository.IMainRepo
import com.assignment.jeeny.repository.MainRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideGitHubRepository(
        registeredPropertyRepository: MainRepo
    ): IMainRepo

}