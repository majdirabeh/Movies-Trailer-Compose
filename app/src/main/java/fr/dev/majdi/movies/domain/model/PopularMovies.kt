package fr.dev.majdi.movies.domain.model

/**
 * Created by Majdi RABEH on 20/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */
data class PopularMovies(
    val page: Int,
    val results: List<Movie>?
)