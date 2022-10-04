package com.lyft.android.ohmymovies.data.source

import com.lyft.android.ohmymovies.data.api.models.MoviesApiModel

interface MoviesDataSource {
    suspend fun getTrending(): List<MoviesApiModel>
    suspend fun getTopRated(): List<MoviesApiModel>
    suspend fun getPopular(): List<MoviesApiModel>
    suspend fun getUpcoming(): List<MoviesApiModel>
}