package com.lyft.android.ohmymovies.data.repository.models

import com.lyft.android.ohmymovies.utils.UiText

data class MoviesSection(
    val title: UiText,
    val movies: List<MovieModel>
)