package fr.dev.majdi.movies.presentation.ui.screens

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.graphics.drawable.BitmapDrawable
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.request.SuccessResult
import fr.dev.majdi.movies.R
import fr.dev.majdi.movies.presentation.ui.common.ComposableLifecycle
import fr.dev.majdi.movies.presentation.ui.utils.getPictureRequest
import fr.dev.majdi.movies.presentation.viewmodel.MovieDetailViewModel

/**
 * Created by Majdi RABEH on 20/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */
@SuppressLint("SourceLockedOrientationActivity")
@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel(),
    navigateToVideoPlayerScreen: (String) -> Unit,
    id: String,
    popBackStack: () -> Unit
) {
    val context = LocalContext.current
    val activity = context as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    val backgroundColorButton by remember { mutableIntStateOf(R.color.purple_200) }

    val movieDetail = viewModel.movieDetail.observeAsState()

    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                //Getting Data from DataBase
                viewModel.getMovieDetailInfo(id.toInt())
            }

            Lifecycle.Event.ON_START -> {

            }

            Lifecycle.Event.ON_RESUME -> {

            }

            Lifecycle.Event.ON_PAUSE -> {

            }

            Lifecycle.Event.ON_STOP -> {

            }

            Lifecycle.Event.ON_DESTROY -> {

            }

            else -> {

            }
        }
    }

    movieDetail.value.let { movie ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Header section with movie title
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                text = movie?.title.toString(),
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold
            )

            // Movie image section
            val pictureUrl = "https://image.tmdb.org/t/p/w600_and_h900_bestv2${movie?.posterUrl}"
            val imageRequest = getPictureRequest(pictureUrl, context)
            val loader = ImageLoader(context)

//            LaunchedEffect(key1 = imageRequest) {
//                val result = (loader.execute(imageRequest) as SuccessResult).drawable
//                val bitmap = (result as BitmapDrawable).bitmap
//                Palette.Builder(bitmap).generate {
//                    it?.let { palette ->
//                        val dominantColor = palette.getDominantColor(
//                            ContextCompat.getColor(
//                                context,
//                                R.color.purple_700
//                            )
//                        )
//                        backgroundColorButton = dominantColor
//                    }
//                }
//            }

            Image(
                painter = rememberAsyncImagePainter(
                    model = imageRequest,
                    contentScale = ContentScale.FillBounds
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(start = 16.dp, bottom = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.FillBounds
            )

            // Other details section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Summary",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = movie?.overview.toString(),
                    style = MaterialTheme.typography.body2
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Release Date",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = movie?.releaseDate.toString(), style = MaterialTheme.typography.body2
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Play button section
            PlayButton(onClick = {
                navigateToVideoPlayerScreen(movie?.id.toString())
            }, backgroundColor = backgroundColorButton)
        }
    }

    BackHandler {
        //We can use this callback if we want to add a toolbar with back button
        popBackStack.invoke()
    }

}

@Composable
fun PlayButton(onClick: () -> Unit, backgroundColor: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor =
                Color(backgroundColor)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow, contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Play Trailer",
                    color = Color.White,
                    style = MaterialTheme.typography.button
                )
            }
        }
    }
}