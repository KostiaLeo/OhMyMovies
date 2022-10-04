package com.lyft.android.ohmymovies.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.lyft.android.ohmymovies.data.local.entities.MovieAndSectionCrossRef
import com.lyft.android.ohmymovies.data.local.entities.MoviesSectionEntity

@Dao
interface SectionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSection(section: MoviesSectionEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSectionsAndMoviesCrossRefs(crossrefs: List<MovieAndSectionCrossRef>)
}