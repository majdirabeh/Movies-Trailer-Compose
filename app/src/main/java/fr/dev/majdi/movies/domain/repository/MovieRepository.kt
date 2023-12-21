package fr.dev.majdi.movies.domain.repository

import fr.dev.majdi.movies.data.source.remote.Resource
import fr.dev.majdi.movies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

/**
 * Created by Majdi RABEH on 20/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */
interface MovieRepository {
    fun getPopularMovies(): Flow<Resource<List<Movie>>>
}