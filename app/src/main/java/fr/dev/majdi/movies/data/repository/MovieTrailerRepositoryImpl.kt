package fr.dev.majdi.movies.data.repository

import fr.dev.majdi.movies.data.source.remote.MovieApiService
import fr.dev.majdi.movies.data.source.remote.Resource
import fr.dev.majdi.movies.domain.model.Trailer
import fr.dev.majdi.movies.domain.repository.MovieTrailerRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Majdi RABEH on 20/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */
class MovieTrailerRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService
) : MovieTrailerRepository {

    override fun getTrailerMovie(movieId: Int): Flow<Resource<Trailer>> = flow {
        emit(Resource.Loading())
        try {
            val remoteTrailerMovie = movieApiService.getMovieTrailer(movieId)
            delay(1000)
            // Emit data from remote
            emit(Resource.Success(remoteTrailerMovie.toTrailer()))
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
    }

}