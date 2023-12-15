package com.example.movieapptmdb.feature.movies.presentation

import com.example.movieapptmdb.feature.movies.domain.model.MovieModel

data class MovieListState (
    val isLoading: Boolean = false,
    val popularMovieListPage: Int = 1,
    val upcomingMovieListPage: Int = 1,
    val isCurrentPopularScreen: Boolean = true,
    val popularMovieList: List<MovieModel> = emptyList(),
    val upcomingMovieList: List<MovieModel> = emptyList(),

)