package fr.dev.majdi.movies.presentation.ui.screens

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.palette.graphics.Palette
import fr.dev.majdi.movies.R
import fr.dev.majdi.movies.domain.model.Movie
import fr.dev.majdi.movies.presentation.ui.common.CoilImage
import fr.dev.majdi.movies.presentation.viewmodel.PopularMovieListViewModel

/**
 * Created by Majdi RABEH on 20/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */
@SuppressLint("SourceLockedOrientationActivity")
@Composable
fun PopularMovieListScreen(
    viewModel: PopularMovieListViewModel = hiltViewModel(),
    navigateToMovieDetailScreen: (Int) -> Unit
) {
    val activity = LocalContext.current as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    val state = viewModel.state.value

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize()
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .wrapContentSize(Alignment.Center)
            )
        } else {
            if (state.moviesList.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Oops, something went wrong!",
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            } else {
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                    items(state.moviesList.orEmpty()) { movie ->
                        MovieCard(movie = movie) {
                            navigateToMovieDetailScreen(it.id)
                        }
                    }
                }
            }
        }

    }

}

@Composable
fun MovieCard(movie: Movie, onMovieClick: (Movie) -> Unit) {

    val backgroundColor = remember { mutableIntStateOf(0) }
    val context = LocalContext.current
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            Color(backgroundColor.value)
        ),
        modifier = Modifier
            .padding(8.dp)
            .wrapContentSize()
            .clickable {
                onMovieClick(movie)
            }
    ) {

        Column(
            modifier = Modifier
                .wrapContentSize()
        ) {
            CoilImage(imageUrl = movie.posterUrl) {
                Palette.Builder(it).generate {
                    it?.let { palette ->
                        val dominantColor = palette.getDominantColor(
                            ContextCompat.getColor(
                                context,
                                R.color.purple_700
                            )
                        )
                        backgroundColor.value = dominantColor
                    }
                }
            }
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .fillMaxWidth()
                    .padding(12.dp),
                text = movie.title,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
}
