package com.lyft.android.ohmymovies.di

import com.lyft.android.ohmymovies.data.repository.MoviesRepository
import com.lyft.android.ohmymovies.data.repository.OnlineMoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindMoviesRepository(remoteMoviesRepository: OnlineMoviesRepository): MoviesRepository
}