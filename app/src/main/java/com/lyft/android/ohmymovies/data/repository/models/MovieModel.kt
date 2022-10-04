package com.lyft.android.ohmymovies.data.repository.models

import androidx.compose.runtime.Immutable

@Immutable
data class MovieModel(
    val id: Int,
    val title: String,
    val posterPath: String,
    val rating: Double
)


private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
fun MovieModel.posterFullUrl(): String {
    return "$IMAGE_BASE_URL/w500$posterPath"
}