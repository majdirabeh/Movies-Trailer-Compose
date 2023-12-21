package fr.dev.majdi.movies.data.repository

import fr.dev.majdi.movies.data.source.local.dao.MovieDao
import fr.dev.majdi.movies.data.source.local.dao.PopularMoviesDao
import fr.dev.majdi.movies.data.source.remote.MovieApiService
import fr.dev.majdi.movies.data.source.remote.Resource
import fr.dev.majdi.movies.domain.model.Movie
import fr.dev.majdi.movies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Majdi RABEH on 20/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */
class MovieRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService,
    private val popularMoviesDao: PopularMoviesDao,
    private val movieDao: MovieDao
) : MovieRepository {

    override fun getPopularMovies(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        try {
            fetchAndInsertPopularMovies(movieApiService, popularMoviesDao, movieDao)
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection."
                )
            )
        }
        // Emit data from db only and not directly from remote
        emit(Resource.Success(getPopularMoviesFromDb(movieDao)))
    }

    private suspend fun fetchAndInsertPopularMovies(
        movieApiService: MovieApiService,
        popularMoviesDao: PopularMoviesDao,
        movieDao: MovieDao
    ) {
        val remotePopularMovies = movieApiService.getPopularMovies()
        popularMoviesDao.insertPopularMovies(remotePopularMovies.toPopularMoviesEntity())
        movieDao.insertMovieList(
            remotePopularMovies.results.map {
                it.toMovieEntity()
            }
        )
    }

    private suspend fun getPopularMoviesFromDb(movieDao: MovieDao): List<Movie> {
        return movieDao.getMovieList().map { it.toMovie() }
    }

}