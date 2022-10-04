package com.lyft.android.ohmymovies.data.local.source

import com.lyft.android.ohmymovies.data.local.dao.MoviesDao
import com.lyft.android.ohmymovies.data.local.dao.SectionDao
import com.lyft.android.ohmymovies.data.local.entities.MovieAndSectionCrossRef
import com.lyft.android.ohmymovies.data.local.entities.MovieEntity
import com.lyft.android.ohmymovies.data.local.entities.MoviesSectionEntity
import com.lyft.android.ohmymovies.data.local.entities.SectionWithMoviesDTO
import com.lyft.android.ohmymovies.utils.DispatcherProvider
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomMoviesLocalDataSource @Inject constructor(
    private val moviesDao: MoviesDao,
    private val sectionDao: SectionDao,
    private val dispatcherProvider: DispatcherProvider
): MoviesLocalDataSource {
    override fun getMoviesSectionsFlow(): Flow<List<SectionWithMoviesDTO>> {
        return moviesDao.getMovieSectionsFlow()
    }

    override suspend fun upsertMoviesToSection(sectionCode: String, movies: List<MovieEntity>) {
        withContext(dispatcherProvider.io) {
            coroutineScope {
                launch { moviesDao.upsertMovies(movies) }
                launch { sectionDao.insertSection(MoviesSectionEntity(sectionCode)) }
            }
            val crossRefs = movies.map { MovieAndSectionCrossRef(it.id, sectionCode) }
            sectionDao.insertSectionsAndMoviesCrossRefs(crossRefs)
        }
    }
}