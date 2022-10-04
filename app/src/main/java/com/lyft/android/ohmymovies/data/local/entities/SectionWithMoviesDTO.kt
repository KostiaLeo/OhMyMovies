package com.lyft.android.ohmymovies.data.local.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class SectionWithMoviesDTO(
    @Embedded val moviesSectionEntity: MoviesSectionEntity,
    @Relation(
        parentColumn = "section_id",
        entityColumn = "movie_id",
        associateBy = Junction(MovieAndSectionCrossRef::class)
    )
    val movies: List<MovieEntity>
)