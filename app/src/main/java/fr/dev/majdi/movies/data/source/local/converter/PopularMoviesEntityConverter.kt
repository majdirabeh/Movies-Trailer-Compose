package fr.dev.majdi.movies.data.source.local.converter

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import fr.dev.majdi.movies.data.source.local.entity.MovieEntity
import fr.dev.majdi.movies.domain.model.Movie

/**
 * Created by Majdi RABEH on 20/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */
class PopularMoviesEntityConverter {

    @TypeConverter
    fun fromStringToMovieList(value: String): List<Movie>? =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
            .adapter<List<Movie>>(Types.newParameterizedType(List::class.java, Movie::class.java))
            .fromJson(value)

    @TypeConverter
    fun fromMovieListTypeToString(movieListType: List<Movie>?): String =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
            .adapter<List<Movie>>(Types.newParameterizedType(List::class.java, Movie::class.java))
            .toJson(movieListType)

    @TypeConverter
    fun fromStringToMovieEntityList(value: String): List<MovieEntity>? =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build().adapter<List<MovieEntity>>(
                Types.newParameterizedType(
                    List::class.java,
                    MovieEntity::class.java
                )
            ).fromJson(value)

    @TypeConverter
    fun fromMovieEntityListTypeToString(movieEntityListType: List<MovieEntity>?): String =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build().adapter<List<MovieEntity>>(
                Types.newParameterizedType(
                    List::class.java,
                    MovieEntity::class.java
                )
            ).toJson(movieEntityListType)

}