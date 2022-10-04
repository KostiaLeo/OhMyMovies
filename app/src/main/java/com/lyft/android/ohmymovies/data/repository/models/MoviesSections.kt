package com.lyft.android.ohmymovies.data.repository.models

import com.lyft.android.ohmymovies.R
import com.lyft.android.ohmymovies.utils.UiText

data class MoviesSection(
    val title: UiText,
    val movies: List<MovieModel>
)

enum class MovieSectionTitle(val code: String, val titleResId: Int) {
    TRENDING("trending", R.string.trending),
    POPULAR("popular", R.string.popular),
    TOP_RATED("top_rated", R.string.top_rated),
    UPCOMING("upcoming", R.string.upcoming)
}