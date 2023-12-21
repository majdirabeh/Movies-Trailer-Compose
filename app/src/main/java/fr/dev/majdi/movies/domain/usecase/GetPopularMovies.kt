package fr.dev.majdi.movies.domain.usecase

import fr.dev.majdi.movies.data.source.remote.Resource
import fr.dev.majdi.movies.domain.model.Movie
import fr.dev.majdi.movies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Majdi RABEH on 20/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */
class GetPopularMovies(private val movieRepository: MovieRepository) {

    operator fun invoke(): Flow<Resource<List<Movie>>> {
        return movieRepository.getPopularMovies()
    }

}