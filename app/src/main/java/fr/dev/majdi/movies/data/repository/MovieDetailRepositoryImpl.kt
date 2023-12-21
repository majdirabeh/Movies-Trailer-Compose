package fr.dev.majdi.movies.data.repository

import fr.dev.majdi.movies.data.source.local.dao.MovieDao
import fr.dev.majdi.movies.domain.model.Movie
import fr.dev.majdi.movies.domain.repository.MovieDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Majdi RABEH on 20/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */
class MovieDetailRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao
) : MovieDetailRepository {


    override fun getMovieDetail(id: Int): Flow<Movie> = flow {
        emit(movieDao.getMovieById(id).toMovie())
    }
}