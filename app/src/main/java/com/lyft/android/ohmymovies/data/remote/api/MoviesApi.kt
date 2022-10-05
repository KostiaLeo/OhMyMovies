package com.lyft.android.ohmymovies.data.remote.api

import com.lyft.android.ohmymovies.data.remote.models.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val MEDIA_TYPE = "media_type"
private const val TIME_WINDOW = "time_window"

interface MoviesApi {

    @GET("trending/{$MEDIA_TYPE}/{$TIME_WINDOW}")
    suspend fun getTrending(
        @Path(MEDIA_TYPE) mediaType: String,
        @Path(TIME_WINDOW) timeWindow: String
    ): MoviesResponse

    @GET("movie/top_rated")
    suspend fun getTopRated(): MoviesResponse

    @GET("movie/popular")
    suspend fun getPopular(@Query("page") page: Int): MoviesResponse

    @GET("movie/upcoming")
    suspend fun getUpcoming(): MoviesResponse
}