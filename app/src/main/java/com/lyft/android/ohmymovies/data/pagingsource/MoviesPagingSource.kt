package com.lyft.android.ohmymovies.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lyft.android.ohmymovies.data.remote.models.MoviesApiModel
import com.lyft.android.ohmymovies.data.remote.source.MoviesRemoteDataSource

class MoviesPagingSource(
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) : PagingSource<Int, MoviesApiModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesApiModel> {
        val pageToLoad = params.key ?: 1
        return try {
            val response = moviesRemoteDataSource.getPopularPaged(pageToLoad)
            val nextKey = response.page.plus(1).takeIf { it <= response.total_pages }
            val prevKey = response.page.minus(1).takeIf { it > 0 }
            LoadResult.Page(
                data = response.results,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MoviesApiModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}