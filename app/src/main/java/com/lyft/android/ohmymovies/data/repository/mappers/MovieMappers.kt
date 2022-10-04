package com.lyft.android.ohmymovies.data.repository.mappers

import com.lyft.android.ohmymovies.data.local.entities.MovieEntity
import com.lyft.android.ohmymovies.data.local.entities.SectionWithMoviesDTO
import com.lyft.android.ohmymovies.data.remote.api.models.MoviesApiModel
import com.lyft.android.ohmymovies.data.repository.models.MovieModel
import com.lyft.android.ohmymovies.data.repository.models.MovieSectionTitle
import com.lyft.android.ohmymovies.data.repository.models.MoviesSection
import com.lyft.android.ohmymovies.utils.UiText

fun MoviesApiModel.asEntity() = MovieEntity(
    id,
    adult,
    backdrop_path,
    genre_ids,
    media_type,
    original_language,
    original_title,
    overview,
    popularity,
    poster_path,
    release_date,
    title,
    video,
    vote_average,
    vote_count
)

fun SectionWithMoviesDTO.asMoviesSectionModel() = MoviesSection(
    title = UiText.StringResource(MovieSectionTitle.values().first { it.code == moviesSectionEntity.id }.titleResId),
    movies = movies.map(MovieEntity::asExternalModel)
)

fun MovieEntity.asExternalModel() = MovieModel(
    id = id,
    title = title,
    posterPath = poster_path,
    rating = vote_average
)
