package fr.dev.majdi.movies.data.source.remote

import fr.dev.majdi.movies.data.source.remote.dto.PopularMoviesDto
import fr.dev.majdi.movies.data.source.remote.dto.TrailerDto
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Majdi RABEH on 20/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */
interface MovieApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(): PopularMoviesDto

    @GET("movie/{movieId}/videos")
    suspend fun getMovieTrailer(@Path("movieId") movieId: Int): TrailerDto

    companion object {
        const val BASE_URL: String = "https://api.themoviedb.org/3/"
    }
}