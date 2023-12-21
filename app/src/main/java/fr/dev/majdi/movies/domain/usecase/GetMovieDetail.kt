package fr.dev.majdi.movies.domain.usecase

import fr.dev.majdi.movies.domain.model.Movie
import fr.dev.majdi.movies.domain.repository.MovieDetailRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Majdi RABEH on 20/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */
class GetMovieDetail(private val movieDetailRepository: MovieDetailRepository) {

    //We can get Detail from network
    fun getMovieDetailFromRemote() {
        //TODO Get Movie from network
    }

    //In my case I will just get it from Database
    fun getMovieDetail(id: Int): Flow<Movie> {
        return movieDetailRepository.getMovieDetail(id)
    }

}