package fr.dev.majdi.movies.data.source.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import fr.dev.majdi.movies.data.source.local.entity.MovieEntity

/**
 * Created by Majdi RABEH on 20/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */
@JsonClass(generateAdapter = true)
data class MovieDto(
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "backdrop_path")
    val backdropPath: String,
    @Json(name = "genre_ids")
    val genreIds: List<Int>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "original_title")
    val originalTitle: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "video")
    val video: Boolean,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int
){
    fun toMovieEntity(): MovieEntity {
        return MovieEntity(
            id = id,
            title = title,
            overview = overview,
            posterUrl = posterPath,
            releaseDate = releaseDate
        )
    }
}