package com.lyft.android.ohmymovies.data.repository.mappers

import com.lyft.android.ohmymovies.data.api.models.MoviesApiModel
import com.lyft.android.ohmymovies.data.repository.models.MovieModel

fun MoviesApiModel.asExternalModel() = MovieModel(
    id = id,
    title = title,
    posterPath = poster_path,
    rating = vote_average
)