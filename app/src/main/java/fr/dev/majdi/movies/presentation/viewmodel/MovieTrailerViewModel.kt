package fr.dev.majdi.movies.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.dev.majdi.movies.data.source.remote.Resource
import fr.dev.majdi.movies.domain.usecase.GetMovieTrailer
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Majdi RABEH on 20/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */
@HiltViewModel
class MovieTrailerViewModel @Inject constructor(private val getMovieTrailer: GetMovieTrailer) :
    ViewModel() {

    private val _state = mutableStateOf(MovieTrailerState())
    val state: State<MovieTrailerState> = _state

    fun getMovieTrailer(movieId: Int) {
        viewModelScope.launch {
            getMovieTrailer.getMovieTrailer(movieId).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            trailers = result.data?.results ?: emptyList(),
                            isLoading = true
                        )
                    }

                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            trailers = result.data?.results ?: emptyList(),
                            isLoading = false
                        )
                    }

                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            trailers = result.data?.results ?: emptyList(),
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

}