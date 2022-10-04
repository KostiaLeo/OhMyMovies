package com.lyft.android.ohmymovies.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.lyft.android.ohmymovies.data.local.entities.MovieEntity
import com.lyft.android.ohmymovies.data.local.entities.SectionWithMoviesDTO
import com.lyft.android.ohmymovies.data.local.upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query("SELECT * FROM sections")
    @Transaction
    fun getMovieSectionsFlow(): Flow<List<SectionWithMoviesDTO>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreMovies(movies: List<MovieEntity>): List<Long>

    @Update
    suspend fun updateMovies(movies: List<MovieEntity>)

    @Transaction
    suspend fun upsertMovies(movies: List<MovieEntity>) = upsert(
        movies,
        ::insertOrIgnoreMovies,
        ::updateMovies
    )
}