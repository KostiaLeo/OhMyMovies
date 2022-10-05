package com.lyft.android.ohmymovies.data.repository.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.lyft.android.ohmymovies.data.pagingsource.MoviesPagingSource
import com.lyft.android.ohmymovies.data.remote.models.MoviesApiModel
import com.lyft.android.ohmymovies.data.remote.source.MoviesRemoteDataSource
import com.lyft.android.ohmymovies.data.repository.mappers.asExternalModel
import com.lyft.android.ohmymovies.data.repository.models.MovieModel
import com.lyft.android.ohmymovies.utils.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteMoviesPagingRepository @Inject constructor(
    private val remoteDataSource: MoviesRemoteDataSource,
    private val dispatcherProvider: DispatcherProvider
) : MoviesPagingRepository {

    override fun moviesPagingFlow(): Flow<PagingData<MovieModel>> {
        return Pager(
            PagingConfig(pageSize = 20, enablePlaceholders = true)
        ) {
            MoviesPagingSource(remoteDataSource)
        }.flow.map { pagingData ->
            pagingData.map(MoviesApiModel::asExternalModel)
        }.flowOn(dispatcherProvider.io)
    }
}