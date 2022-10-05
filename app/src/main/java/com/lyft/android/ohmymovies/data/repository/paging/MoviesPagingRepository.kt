package com.lyft.android.ohmymovies.data.repository.paging

import androidx.paging.PagingData
import com.lyft.android.ohmymovies.data.repository.models.MovieModel
import kotlinx.coroutines.flow.Flow

interface MoviesPagingRepository {
    fun moviesPagingFlow(): Flow<PagingData<MovieModel>>
}