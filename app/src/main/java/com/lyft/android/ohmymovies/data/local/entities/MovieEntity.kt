package com.lyft.android.ohmymovies.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey @ColumnInfo(name = "movie_id") val id: Int = 0,
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val genre_ids: List<Int> = listOf(),
    val media_type: String = "",
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = 0.0,
    val vote_count: Int = 0
)
