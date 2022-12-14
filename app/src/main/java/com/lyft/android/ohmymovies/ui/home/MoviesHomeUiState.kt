package com.lyft.android.ohmymovies.ui.home

import com.lyft.android.ohmymovies.data.repository.models.MoviesSection
import com.lyft.android.ohmymovies.utils.UiText

data class MoviesHomeUiState(
    val isLoading: Boolean = false,
    val sections: List<MoviesSection> = emptyList(),
    val errorMessage: UiText? = null
)