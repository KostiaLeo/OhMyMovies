package com.lyft.android.ohmymovies.di

import com.lyft.android.ohmymovies.data.source.MoviesDataSource
import com.lyft.android.ohmymovies.data.source.RetrofitMoviesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    @Singleton
    fun bindMoviesRemoteDataSource(retrofitDataSource: RetrofitMoviesDataSource): MoviesDataSource
}