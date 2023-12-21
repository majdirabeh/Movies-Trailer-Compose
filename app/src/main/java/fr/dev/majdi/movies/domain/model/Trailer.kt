package fr.dev.majdi.movies.domain.model

data class Trailer(
    val id: Int,
    val results: List<Result>
)