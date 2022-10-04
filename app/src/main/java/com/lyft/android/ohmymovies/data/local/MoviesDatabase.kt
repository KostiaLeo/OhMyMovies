package com.lyft.android.ohmymovies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lyft.android.ohmymovies.data.local.dao.MoviesDao
import com.lyft.android.ohmymovies.data.local.dao.SectionDao
import com.lyft.android.ohmymovies.data.local.entities.MovieAndSectionCrossRef
import com.lyft.android.ohmymovies.data.local.entities.MovieEntity
import com.lyft.android.ohmymovies.data.local.entities.MoviesSectionEntity

@Database(entities = [MovieEntity::class, MoviesSectionEntity::class, MovieAndSectionCrossRef::class], version = 1)
@androidx.room.TypeConverters(Converters::class)
abstract class MoviesDatabase : RoomDatabase() {
    abstract val moviesDao: MoviesDao
    abstract val sectionDao: SectionDao
}