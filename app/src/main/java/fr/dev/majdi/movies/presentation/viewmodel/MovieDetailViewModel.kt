package fr.dev.majdi.movies.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.dev.majdi.movies.domain.model.Movie
import fr.dev.majdi.movies.domain.usecase.GetMovieDetail
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Majdi RABEH on 20/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */
@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val getMovieDetail: GetMovieDetail) :
    ViewModel() {

    private val _movieDetail = MutableLiveData<Movie>(null)
    val movieDetail: LiveData<Movie> get() = _movieDetail


    fun getMovieDetailInfo(movieId: Int) {
        viewModelScope.launch {
            getMovieDetail.getMovieDetail(movieId).collect { movie ->
                _movieDetail.value = movie
            }
        }
    }

}