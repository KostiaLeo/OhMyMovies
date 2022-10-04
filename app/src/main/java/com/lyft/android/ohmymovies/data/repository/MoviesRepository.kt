package com.lyft.android.ohmymovies.data.repository

import com.lyft.android.ohmymovies.data.repository.models.MovieModel
import com.lyft.android.ohmymovies.data.repository.models.MoviesSection

interface MoviesRepository {
    suspend fun getMoviesSections(): List<MoviesSection>
}