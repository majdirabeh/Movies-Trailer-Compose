package fr.dev.majdi.movies.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import fr.dev.majdi.movies.domain.model.Movie

/**
 * Created by Majdi RABEH on 20/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */
@Entity(tableName = MovieEntity.TABLE_NAME)
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String,
    @ColumnInfo(name = "poster_url") val posterUrl: String,
    @ColumnInfo(name = "release_date") val releaseDate: String
) {

    fun toMovie(): Movie {
        return Movie(
            id = id,
            title = title,
            overview = overview,
            posterUrl = posterUrl,
            releaseDate = releaseDate
        )
    }

    companion object {
        const val TABLE_NAME = "movie"
    }
}