package fr.dev.majdi.movies.data.source.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import fr.dev.majdi.movies.domain.model.Result

@JsonClass(generateAdapter = true)
data class ResultDto(
    @Json(name = "id")
    val id: String,
    @Json(name = "iso_3166_1")
    val isoOne: String,
    @Json(name = "iso_639_1")
    val isoTwo: String,
    @Json(name = "key")
    val key: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "official")
    val official: Boolean,
    @Json(name = "published_at")
    val publishedAt: String,
    @Json(name = "site")
    val site: String,
    @Json(name = "size")
    val size: Int,
    @Json(name = "type")
    val type: String
) {
    fun toResult(): Result {
        return Result(
            id = id,
            isoOne = isoOne,
            isoTwo = isoTwo,
            key = key,
            name = name,
            official = official,
            publishedAt = publishedAt,
            site = site,
            size = size,
            type = type
        )
    }
}