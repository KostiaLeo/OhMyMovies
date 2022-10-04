package com.lyft.android.ohmymovies.di

import com.lyft.android.ohmymovies.data.local.source.MoviesLocalDataSource
import com.lyft.android.ohmymovies.data.local.source.RoomMoviesLocalDataSource
import com.lyft.android.ohmymovies.data.remote.source.MoviesRemoteDataSource
import com.lyft.android.ohmymovies.data.remote.source.RetrofitMoviesRemoteDataSource
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
    fun bindMoviesRemoteDataSource(retrofitDataSource: RetrofitMoviesRemoteDataSource): MoviesRemoteDataSource

    @Binds
    @Singleton
    fun bindMoviesLocalDataSource(roomMoviesLocalDataSource: RoomMoviesLocalDataSource): MoviesLocalDataSource

}