package fr.dev.majdi.movies.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.dev.majdi.movies.data.source.local.converter.PopularMoviesEntityConverter
import fr.dev.majdi.movies.data.source.local.dao.MovieDao
import fr.dev.majdi.movies.data.source.local.dao.PopularMoviesDao
import fr.dev.majdi.movies.data.source.local.entity.MovieEntity
import fr.dev.majdi.movies.data.source.local.entity.PopularMoviesEntity

/**
 * Created by Majdi RABEH on 20/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */
@Database(entities = [PopularMoviesEntity::class, MovieEntity::class], version = 1, exportSchema = false)
@TypeConverters(PopularMoviesEntityConverter::class)
abstract class AppDatabase : RoomDatabase(){

    abstract fun getMovieDao(): MovieDao

    abstract fun getPopularMoviesDao(): PopularMoviesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "movie_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}