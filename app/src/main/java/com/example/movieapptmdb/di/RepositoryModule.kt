package com.example.movieapptmdb.di

import com.example.movieapptmdb.feature.movies.data.repository.MoviesRepositoryImpl
import com.example.movieapptmdb.feature.movies.domain.repository.MovieListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        moviesRepositoryImpl: MoviesRepositoryImpl
    ): MovieListRepository
}