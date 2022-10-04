package com.lyft.android.ohmymovies.data.repository

import com.lyft.android.ohmymovies.data.repository.models.MoviesSection
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    val moviesSectionsFlow: Flow<List<MoviesSection>>
    suspend fun syncMovies()
}