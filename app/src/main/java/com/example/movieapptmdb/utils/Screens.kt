package com.example.movieapptmdb.utils

sealed class Screens(val route: String) {
    object Home : Screens("main")
    object PopularMovieListScreen : Screens("popularMovieList")
    object UpcomingMovieListScreen : Screens("upcomingMovieList")
    object DetailsScreen : Screens("details")
}