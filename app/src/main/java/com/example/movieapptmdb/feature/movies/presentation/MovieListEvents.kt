package com.example.movieapptmdb.feature.movies.presentation

sealed interface MovieListEvents {
    data class Paginate(val category: String): MovieListEvents
    object Navigate: MovieListEvents
}