package fr.dev.majdi.movies.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import fr.dev.majdi.movies.domain.model.PopularMovies

/**
 * Created by Majdi RABEH on 20/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */
@Entity(tableName = PopularMoviesEntity.TABLE_NAME)
class PopularMoviesEntity(
    @PrimaryKey(autoGenerate = true)
    val primaryKeyId: Int? = null,
    val page: Int,
    val results: List<MovieEntity>
) {

    fun toPopularMovies(): PopularMovies {
        return PopularMovies(
            page = page,
            results.map { it.toMovie() }
        )
    }

    companion object {
        const val TABLE_NAME = "popular_movies"
    }
}