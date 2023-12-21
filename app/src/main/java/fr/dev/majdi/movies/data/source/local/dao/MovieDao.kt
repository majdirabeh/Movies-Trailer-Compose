package fr.dev.majdi.movies.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.dev.majdi.movies.data.source.local.entity.MovieEntity

/**
 * Created by Majdi RABEH on 20/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */
@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(movies: List<MovieEntity>)

    @Query("SELECT * FROM ${MovieEntity.TABLE_NAME}")
    suspend fun getMovieList(): List<MovieEntity>

    @Query("SELECT * FROM ${MovieEntity.TABLE_NAME} where id = :movieId")
    suspend fun getMovieById(movieId: Int): MovieEntity

    @Query("DELETE FROM ${MovieEntity.TABLE_NAME}")
    suspend fun deleteAll()

}