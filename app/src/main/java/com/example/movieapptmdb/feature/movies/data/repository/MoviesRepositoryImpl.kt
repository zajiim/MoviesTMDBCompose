package com.example.movieapptmdb.feature.movies.data.repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.movieapptmdb.feature.movies.data.local.movie.MovieDatabase
import com.example.movieapptmdb.feature.movies.data.mappers.toMovie
import com.example.movieapptmdb.feature.movies.data.mappers.toMovieEntity
import com.example.movieapptmdb.feature.movies.data.remote.movie.MovieApi
import com.example.movieapptmdb.feature.movies.domain.model.MovieModel
import com.example.movieapptmdb.feature.movies.domain.repository.MovieListRepository
import com.example.movieapptmdb.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDb: MovieDatabase,
) : MovieListRepository {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int,
    ): Flow<Resource<List<MovieModel>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val localMovieList = movieDb.movieDao.getMovieListByCategory(category)
            val shouldLoadLocalMovie = localMovieList.isNotEmpty() && !forceFetchFromRemote

            if (shouldLoadLocalMovie) {
                emit(Resource.Success(
                    data = localMovieList.map { movieEntity ->
                        movieEntity.toMovie(category)
                    }
                ))
                emit(Resource.Loading(isLoading = false))
                return@flow
            }

            val movieListFromRemoteApi = try {
                movieApi.getMoviesList(
                    category = category,
                    page = page
                )
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            }


            val movieEntities = movieListFromRemoteApi.results.let {
                it.map { movieDto ->
                    movieDto.toMovieEntity(category)
                }
            }

            movieDb.movieDao.upsertMovieList(movieEntities)
            emit(Resource.Success(movieEntities.map { it.toMovie(category) }))
            emit(Resource.Loading(isLoading = false))

        }
    }

    override suspend fun getMovie(id: Int): Flow<Resource<MovieModel>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val movieEntity = movieDb.movieDao.getMovieById(id)


            if (movieEntity != null) {
                emit(Resource.Success(movieEntity.toMovie(movieEntity.category)))

                emit(Resource.Loading(isLoading = false))
                return@flow
            }

            emit(Resource.Error("No such movie found"))
            emit(Resource.Loading(isLoading = false))


        }
    }
}