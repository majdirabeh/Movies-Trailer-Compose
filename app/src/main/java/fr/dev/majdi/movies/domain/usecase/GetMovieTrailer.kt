package fr.dev.majdi.movies.domain.usecase

import fr.dev.majdi.movies.data.source.remote.Resource
import fr.dev.majdi.movies.domain.model.Trailer
import fr.dev.majdi.movies.domain.repository.MovieTrailerRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Majdi RABEH on 21/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */
class GetMovieTrailer(private val movieTrailerRepository: MovieTrailerRepository) {

    fun getMovieTrailer(movieId: Int): Flow<Resource<Trailer>> {
        return movieTrailerRepository.getTrailerMovie(movieId)
    }
}