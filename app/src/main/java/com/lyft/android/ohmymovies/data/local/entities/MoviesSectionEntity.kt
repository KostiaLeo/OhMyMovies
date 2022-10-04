package com.lyft.android.ohmymovies.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sections")
data class MoviesSectionEntity(
    @PrimaryKey @ColumnInfo(name = "section_id") val id: String
)
