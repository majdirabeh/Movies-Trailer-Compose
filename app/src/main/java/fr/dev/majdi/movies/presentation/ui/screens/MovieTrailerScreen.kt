package fr.dev.majdi.movies.presentation.ui.screens

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import fr.dev.majdi.movies.presentation.viewmodel.MovieTrailerViewModel

/**
 * Created by Majdi RABEH on 20/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */
@SuppressLint("OpaqueUnitKey", "SourceLockedOrientationActivity", "DEPRECATION")
@Composable
fun VideoPlayerScreen(
    viewModel: MovieTrailerViewModel = hiltViewModel(),
    id: String,
    popBackStack: () -> Unit
) {
    val activity = LocalContext.current as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

    val systemUiController: SystemUiController = rememberSystemUiController()

    val state = viewModel.state.value
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)
    val videoId = remember {
        mutableStateOf("")
    }
    state.trailers.let { listTrailer ->

        listTrailer.filter {
            it.type == "Trailer" && it.name.contains("Trailer")
        }.map {
            //_trailerLink.value = "https://www.youtube.com/watch?v=${it.key}"
            videoId.value = it.key
        }
    }

    DisposableEffect(key1 = true) {
        systemUiController.isStatusBarVisible = false //hide status bar
        onDispose {
            systemUiController.isStatusBarVisible = true // show it when leave the screen
        }
    }

    DisposableEffect(key1 =
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .wrapContentSize()
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
            if (state.trailers.isNullOrEmpty()) {
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
                AndroidView(
                    modifier = Modifier
                        .wrapContentSize()
                        .background(Color.Black),
                    factory = { context ->
                        YouTubePlayerView(context = context).apply {
                            lifecycleOwner.value.lifecycle.addObserver(this)
                            addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                                override fun onReady(youTubePlayer: YouTubePlayer) {
                                    youTubePlayer.loadVideo(videoId.value, 0f)
                                }
                            })
                        }
                    })
            }

        }
    },
        effect = {
            val observer = LifecycleEventObserver { _, event ->
                when (event) {
                    Lifecycle.Event.ON_RESUME -> {

                    }

                    Lifecycle.Event.ON_PAUSE -> {

                    }

                    Lifecycle.Event.ON_CREATE -> {
                        //Call API for getting trailer
                        viewModel.getMovieTrailer(id.toInt())
                    }

                    Lifecycle.Event.ON_START -> {

                    }

                    Lifecycle.Event.ON_STOP -> {

                    }

                    Lifecycle.Event.ON_DESTROY -> {

                    }

                    Lifecycle.Event.ON_ANY -> {
                        //To be implement
                    }
                }
            }

            val lifecycle = lifecycleOwner.value.lifecycle
            lifecycle.addObserver(observer)

            onDispose {
                lifecycle.removeObserver(observer)
            }
        })

    BackHandler {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        popBackStack.invoke()
    }

}