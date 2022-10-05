package com.lyft.android.ohmymovies.domain

import com.lyft.android.ohmymovies.data.repository.paging.MoviesPagingRepository
import javax.inject.Inject

class ObservePagingMoviesUseCase @Inject constructor(
    private val moviesPagingRepository: MoviesPagingRepository
) {
    operator fun invoke() = moviesPagingRepository.moviesPagingFlow()
}