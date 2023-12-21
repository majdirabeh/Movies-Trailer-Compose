package fr.dev.majdi.movies.di.appmodule

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.dev.majdi.movies.data.source.local.AppDatabase
import javax.inject.Singleton

/**
 * Created by Majdi RABEH on 20/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
class AppDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = AppDatabase.getDatabase(application)

    @Singleton
    @Provides
    fun providePopularMoviesDao(database: AppDatabase) = database.getPopularMoviesDao()

    @Singleton
    @Provides
    fun provideMovieDao(database: AppDatabase) = database.getMovieDao()

}