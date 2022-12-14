package com.lyft.android.ohmymovies.domain

import com.lyft.android.ohmymovies.data.repository.MoviesRepository
import javax.inject.Inject


class ObserveMoviesSectionsUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke() = moviesRepository.moviesSectionsFlow
}