package fr.dev.majdi.movies.data.source.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import fr.dev.majdi.movies.domain.model.Trailer

@JsonClass(generateAdapter = true)
data class TrailerDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "results")
    val results: List<ResultDto>
) {
    fun toTrailer(): Trailer {
        return Trailer(
            id = id,
            results = results.map { it.toResult() }
        )
    }
}