package com.lyft.android.ohmymovies.data.remote.models

data class MoviesResponse(
    val page: Int = 0,
    val results: List<MoviesApiModel> = listOf(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)