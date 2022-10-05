package com.lyft.android.ohmymovies.data.remote.source

import com.lyft.android.ohmymovies.data.remote.api.MoviesApi
import com.lyft.android.ohmymovies.data.remote.models.MoviesApiModel
import com.lyft.android.ohmymovies.data.remote.models.MoviesResponse
import javax.inject.Inject

private const val DEFAULT_MEDIA_TYPE = "movie"
private const val DEFAULT_TIME_WINDOW = "week"

class RetrofitMoviesRemoteDataSource @Inject constructor(
    private val moviesApi: MoviesApi
): MoviesRemoteDataSource {

    override suspend fun getTrending(): List<MoviesApiModel> {
        return moviesApi.getTrending(DEFAULT_MEDIA_TYPE, DEFAULT_TIME_WINDOW).results
    }

    override suspend fun getTopRated(): List<MoviesApiModel> {
        return moviesApi.getTopRated().results
    }

    override suspend fun getPopularPaged(page: Int): MoviesResponse {
        return moviesApi.getPopular(page)
    }

    override suspend fun getPopular(): List<MoviesApiModel> {
        return moviesApi.getPopular(1).results
    }

    override suspend fun getUpcoming(): List<MoviesApiModel> {
        return moviesApi.getUpcoming().results
    }
}