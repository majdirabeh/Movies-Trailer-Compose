package fr.dev.majdi.movies.presentation.ui.common

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.request.SuccessResult
import fr.dev.majdi.movies.presentation.ui.utils.getPictureRequest
import kotlinx.coroutines.launch

/**
 * Created by Majdi RABEH on 20/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */

@Composable
fun CoilImage(imageUrl: String, onImageBitmap: (Bitmap) -> Unit) {
    val context = LocalContext.current
    val pictureUrl = "https://image.tmdb.org/t/p/w600_and_h900_bestv2${imageUrl}"
    val pictureRequest = getPictureRequest(pictureUrl, context)
    val loader = ImageLoader(context)

    LaunchedEffect(key1 = pictureRequest) {
        val result = (loader.execute(pictureRequest) as SuccessResult).drawable
        val bitmap = (result as BitmapDrawable).bitmap
        onImageBitmap(bitmap)
    }

    Image(
        painter = rememberAsyncImagePainter(
            model = pictureRequest,
            contentScale = ContentScale.Crop
        ),
        contentDescription = "Image",
        modifier = Modifier
            .width(600.dp)
            .height(300.dp)
            .clip(RoundedCornerShape(4.dp))
    )
}

/**
 * Composable for detect lifecycle on compose screen
 */
@Composable
fun ComposableLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onEvent: (LifecycleOwner, Lifecycle.Event) -> Unit
) {

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { source, event ->
            onEvent(source, event)
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}