package fr.dev.majdi.movies.domain.model

data class Result(
    val id: String,
    val isoOne: String,
    val isoTwo: String,
    val key: String,
    val name: String,
    val official: Boolean,
    val publishedAt: String,
    val site: String,
    val size: Int,
    val type: String
)