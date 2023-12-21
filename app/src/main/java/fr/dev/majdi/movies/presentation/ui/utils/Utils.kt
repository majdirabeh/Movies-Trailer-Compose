package fr.dev.majdi.movies.presentation.ui.utils

import android.content.Context
import coil.request.CachePolicy
import coil.request.ImageRequest
import fr.dev.majdi.movies.R

/**
 * Created by Majdi RABEH on 21/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */
fun getPictureRequest(pictureUrl: String, context: Context) = ImageRequest.Builder(context)
    .data(pictureUrl)
    .allowHardware(false)
    .placeholder(R.drawable.placeholder)
    .error(R.drawable.placeholder)
    .diskCacheKey(pictureUrl)
    .memoryCacheKey(pictureUrl)
    .diskCachePolicy(CachePolicy.ENABLED)
    .memoryCachePolicy(CachePolicy.ENABLED)
    .build()