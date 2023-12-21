package fr.dev.majdi.movies.di.appmodule

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.dev.majdi.movies.data.repository.MovieDetailRepositoryImpl
import fr.dev.majdi.movies.data.repository.MovieRepositoryImpl
import fr.dev.majdi.movies.data.repository.MovieTrailerRepositoryImpl
import fr.dev.majdi.movies.data.source.local.dao.MovieDao
import fr.dev.majdi.movies.data.source.local.dao.PopularMoviesDao
import fr.dev.majdi.movies.data.source.remote.MovieApiService
import fr.dev.majdi.movies.domain.repository.MovieDetailRepository
import fr.dev.majdi.movies.domain.repository.MovieRepository
import fr.dev.majdi.movies.domain.repository.MovieTrailerRepository
import javax.inject.Singleton

/**
 * Created by Majdi RABEH on 20/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
class AppRepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepositoryImpl(
        movieApiService: MovieApiService,
        popularMoviesDao: PopularMoviesDao,
        movieDao: MovieDao
    ): MovieRepository = MovieRepositoryImpl(movieApiService, popularMoviesDao, movieDao)

    @Provides
    @Singleton
    fun provideMovieDetailRepositoryImpl(
        movieDao: MovieDao
    ): MovieDetailRepository = MovieDetailRepositoryImpl(movieDao)

    @Provides
    @Singleton
    fun provideMovieTrailRepositoryImpl(
        movieApiService: MovieApiService
    ): MovieTrailerRepository = MovieTrailerRepositoryImpl(movieApiService)

}