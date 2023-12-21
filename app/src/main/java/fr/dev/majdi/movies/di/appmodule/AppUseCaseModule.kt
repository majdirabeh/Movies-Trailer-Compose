package fr.dev.majdi.movies.di.appmodule

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.dev.majdi.movies.domain.repository.MovieDetailRepository
import fr.dev.majdi.movies.domain.repository.MovieRepository
import fr.dev.majdi.movies.domain.repository.MovieTrailerRepository
import fr.dev.majdi.movies.domain.usecase.GetMovieDetail
import fr.dev.majdi.movies.domain.usecase.GetMovieTrailer
import fr.dev.majdi.movies.domain.usecase.GetPopularMovies
import javax.inject.Singleton

/**
 * Created by Majdi RABEH on 20/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
class AppUseCaseModule {
    @Provides
    @Singleton
    fun provideGetPopularMoviesUseCase(repository: MovieRepository): GetPopularMovies =
        GetPopularMovies(repository)

    @Provides
    @Singleton
    fun provideGetMovieDetailUseCase(movieDetailRepository: MovieDetailRepository): GetMovieDetail =
        GetMovieDetail(movieDetailRepository)

    @Provides
    @Singleton
    fun provideGetMovieTrailerUseCase(movieTrailerRepository: MovieTrailerRepository): GetMovieTrailer =
        GetMovieTrailer(movieTrailerRepository)


}