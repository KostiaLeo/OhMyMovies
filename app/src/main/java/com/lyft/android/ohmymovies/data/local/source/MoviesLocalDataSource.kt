package com.lyft.android.ohmymovies.data.local.source

import com.lyft.android.ohmymovies.data.local.entities.MovieEntity
import com.lyft.android.ohmymovies.data.local.entities.SectionWithMoviesDTO
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {
    fun getMoviesSectionsFlow(): Flow<List<SectionWithMoviesDTO>>
    suspend fun upsertMoviesToSection(sectionCode: String, movies: List<MovieEntity>)
}