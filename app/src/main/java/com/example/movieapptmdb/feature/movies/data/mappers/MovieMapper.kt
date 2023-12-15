package com.example.movieapptmdb.feature.movies.data.mappers

import com.example.movieapptmdb.feature.movies.data.local.movie.MovieEntity
import com.example.movieapptmdb.feature.movies.data.remote.movie.dto.MovieDto
import com.example.movieapptmdb.feature.movies.domain.model.MovieModel
import java.lang.Exception

fun MovieEntity.toMovie(
    category: String
): MovieModel{
    return MovieModel(
        id = id,
        backdrop_path = backdrop_path,
        original_language = original_language,
        overview = overview,
        poster_path = poster_path,
        popularity = popularity,
        release_date = release_date,
        title = title,
        vote_average = vote_average,
        vote_count = vote_count,
        video = video,
        adult = adult,
        original_title = original_title,
        category = category,
        genre_ids = try {
            genre_ids.split(",").map { it.toInt() }
        } catch (e: Exception) {
            listOf(-1, -2)
        }
    )
}

fun MovieDto.toMovieEntity(
    category: String
): MovieEntity {
    return MovieEntity(
        adult = adult ?: false,
        backdrop_path = backdrop_path ?: "",
        original_language = original_language ?: "",
        original_title = original_title ?: "",
        video = video ?: false,
        popularity = popularity ?: 0.0,
        poster_path = poster_path ?: "",
        overview = overview ?: "",
        title = title ?: "",
        vote_count = vote_count ?: 0,
        vote_average = vote_average ?: 0.0,
        id = id ?: -1,
        category = category,
        release_date = release_date ?: "",
        genre_ids = try {
            genre_ids?.joinToString(",") ?: "-1,-2"
        } catch (e: Exception) {
            "-1, -2"
        }
    )
}