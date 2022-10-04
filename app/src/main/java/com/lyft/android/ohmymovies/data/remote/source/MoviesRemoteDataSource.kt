package com.lyft.android.ohmymovies.data.remote.source

import com.lyft.android.ohmymovies.data.remote.api.models.MoviesApiModel

interface MoviesRemoteDataSource {
    suspend fun getTrending(): List<MoviesApiModel>
    suspend fun getTopRated(): List<MoviesApiModel>
    suspend fun getPopular(): List<MoviesApiModel>
    suspend fun getUpcoming(): List<MoviesApiModel>
}