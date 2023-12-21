package fr.dev.majdi.movies.presentation.viewmodel

import fr.dev.majdi.movies.domain.model.Result

/**
 * Created by Majdi RABEH on 21/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */
data class MovieTrailerState(
    val trailers: List<Result> = emptyList(),
    val isLoading: Boolean = true
)