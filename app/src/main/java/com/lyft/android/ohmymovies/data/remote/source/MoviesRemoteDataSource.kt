package com.lyft.android.ohmymovies.data.remote.source

import com.lyft.android.ohmymovies.data.remote.models.MoviesApiModel
import com.lyft.android.ohmymovies.data.remote.models.MoviesResponse

interface MoviesRemoteDataSource {
    suspend fun getTrending(): List<MoviesApiModel>
    suspend fun getTopRated(): List<MoviesApiModel>
    suspend fun getPopularPaged(page: Int = 1): MoviesResponse
    suspend fun getPopular(): List<MoviesApiModel>
    suspend fun getUpcoming(): List<MoviesApiModel>
}