package com.lyft.android.ohmymovies.data.source

import com.lyft.android.ohmymovies.data.api.MoviesApi
import com.lyft.android.ohmymovies.data.api.models.MoviesApiModel
import com.lyft.android.ohmymovies.data.api.models.MoviesResponse
import javax.inject.Inject

private const val DEFAULT_MEDIA_TYPE = "movie"
private const val DEFAULT_TIME_WINDOW = "week"

class RetrofitMoviesDataSource @Inject constructor(
    private val moviesApi: MoviesApi
): MoviesDataSource {
    override suspend fun getTrending(): List<MoviesApiModel> {
        return moviesApi.getTrending(DEFAULT_MEDIA_TYPE, DEFAULT_TIME_WINDOW).results
    }

    override suspend fun getTopRated(): List<MoviesApiModel> {
        return moviesApi.getTopRated().results
    }

    override suspend fun getPopular(): List<MoviesApiModel> {
        return moviesApi.getPopular().results
    }

    override suspend fun getUpcoming(): List<MoviesApiModel> {
        return moviesApi.getUpcoming().results
    }
}