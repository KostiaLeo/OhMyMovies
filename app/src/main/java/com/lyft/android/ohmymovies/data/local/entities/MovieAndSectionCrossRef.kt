package com.lyft.android.ohmymovies.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "section_to_movie_refs", primaryKeys = ["movie_id", "section_id"],
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["movie_id"],
            childColumns = ["movie_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = MoviesSectionEntity::class,
            parentColumns = ["section_id"],
            childColumns = ["section_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["movie_id"]),
        Index(value = ["section_id"])
    ]
)
data class MovieAndSectionCrossRef(
    @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "section_id") val sectionId: String
)