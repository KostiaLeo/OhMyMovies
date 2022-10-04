package com.lyft.android.ohmymovies.data.repository

import com.lyft.android.ohmymovies.R
import com.lyft.android.ohmymovies.data.api.models.MoviesApiModel
import com.lyft.android.ohmymovies.data.repository.mappers.asExternalModel
import com.lyft.android.ohmymovies.data.repository.models.MovieModel
import com.lyft.android.ohmymovies.data.repository.models.MoviesSection
import com.lyft.android.ohmymovies.data.source.MoviesDataSource
import com.lyft.android.ohmymovies.utils.DispatcherProvider
import com.lyft.android.ohmymovies.utils.UiText
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OnlineMoviesRepository @Inject constructor(
    private val moviesDataSource: MoviesDataSource,
    private val dispatcherProvider: DispatcherProvider
) : MoviesRepository {
    override suspend fun getMoviesSections(): List<MoviesSection> =
        withContext(dispatcherProvider.io) {
            val collectionsAsync = listOf(
                R.string.trending to async { moviesDataSource.getTrending() },
                R.string.popular to async { moviesDataSource.getPopular() },
                R.string.top_rated to async { moviesDataSource.getTopRated() },
                R.string.upcoming to async { moviesDataSource.getUpcoming() }
            )

            collectionsAsync.mapNotNull { (titleResId, moviesAsync) ->
                val movies = kotlin.runCatching { moviesAsync.await() }.getOrNull() ?: return@mapNotNull null
                MoviesSection(
                    title = UiText.StringResource(titleResId),
                    movies.map(MoviesApiModel::asExternalModel)
                )
            }.takeUnless { it.isEmpty() } ?: error("Error loading movies")
        }
}