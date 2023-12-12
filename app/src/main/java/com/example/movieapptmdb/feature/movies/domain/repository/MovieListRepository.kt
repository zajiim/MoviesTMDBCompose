package com.example.movieapptmdb.feature.movies.domain.repository

import com.example.movieapptmdb.feature.movies.domain.model.MovieModel
import com.example.movieapptmdb.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MovieListRepository {

    suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int,
        ): Flow<Resource<List<MovieModel>>>

    suspend fun getMovie(id: Int): Flow<Resource<MovieModel>>
}