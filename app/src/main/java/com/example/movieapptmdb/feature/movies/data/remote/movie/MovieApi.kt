package com.example.movieapptmdb.feature.movies.data.remote.movie

import com.example.movieapptmdb.feature.movies.data.remote.movie.dto.MovieDto
import com.example.movieapptmdb.feature.movies.data.remote.movie.dto.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/{category}")
    suspend fun getMoviesList(
        @Path("category") category: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieListDto


    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
        const val API_KEY = "13b23be2f8b4fd4169d3907add1ad99f"
    }
}