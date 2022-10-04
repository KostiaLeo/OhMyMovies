package com.lyft.android.ohmymovies.data.repository

import com.lyft.android.ohmymovies.data.local.entities.SectionWithMoviesDTO
import com.lyft.android.ohmymovies.data.local.source.MoviesLocalDataSource
import com.lyft.android.ohmymovies.data.remote.api.models.MoviesApiModel
import com.lyft.android.ohmymovies.data.remote.source.MoviesRemoteDataSource
import com.lyft.android.ohmymovies.data.repository.mappers.asEntity
import com.lyft.android.ohmymovies.data.repository.mappers.asMoviesSectionModel
import com.lyft.android.ohmymovies.data.repository.models.MovieSectionTitle
import com.lyft.android.ohmymovies.data.repository.models.MoviesSection
import com.lyft.android.ohmymovies.utils.DispatcherProvider
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OfflineFirstMoviesRepository @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val moviesLocalDataSource: MoviesLocalDataSource,
    private val dispatcherProvider: DispatcherProvider
) : MoviesRepository {

    override val moviesSectionsFlow: Flow<List<MoviesSection>> =
        moviesLocalDataSource.getMoviesSectionsFlow()
            .map { dtos ->
                dtos.map(SectionWithMoviesDTO::asMoviesSectionModel)
            }

    override suspend fun syncMovies() {
        withContext(dispatcherProvider.io) {
            val collectionsAsync = listOf(
                MovieSectionTitle.TRENDING to async { moviesRemoteDataSource.getTrending() },
                MovieSectionTitle.POPULAR to async { moviesRemoteDataSource.getPopular() },
                MovieSectionTitle.TOP_RATED to async { moviesRemoteDataSource.getTopRated() },
                MovieSectionTitle.UPCOMING to async { moviesRemoteDataSource.getUpcoming() }
            )
            collectionsAsync.forEach { (section, moviesAsync) ->
                val moviesApiModels = moviesAsync.await()
                moviesLocalDataSource.upsertMoviesToSection(section.code, moviesApiModels.map(MoviesApiModel::asEntity))
            }
        }
    }
}