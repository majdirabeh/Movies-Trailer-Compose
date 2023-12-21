package fr.dev.majdi.movies.presentation.viewmodel

import fr.dev.majdi.movies.domain.model.Movie

/**
 * Created by Majdi RABEH on 20/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */
data class MovieUiState(
    val moviesList: List<Movie> = emptyList(),
    val isLoading: Boolean = false
)