package fr.dev.majdi.movies.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import fr.dev.majdi.movies.presentation.ui.screens.MovieDetailScreen
import fr.dev.majdi.movies.presentation.ui.screens.PopularMovieListScreen
import fr.dev.majdi.movies.presentation.ui.screens.VideoPlayerScreen

/**
 * Created by Majdi RABEH on 20/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = NavRoute.PopularMovieList.path
    ) {
        addPopularMovieListScreen(navController, this)
        addMovieDetailScreen(navController, this)
        addVideoPlayerScreen(navController, this)
    }
}

private fun addPopularMovieListScreen(
    navController: NavHostController, navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.PopularMovieList.path) {
        PopularMovieListScreen(navigateToMovieDetailScreen = {
            navController.navigate(NavRoute.MovieDetail.withArgs(it.toString()))
        })
    }
}

private fun addMovieDetailScreen(
    navController: NavHostController, navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(
        route = NavRoute.MovieDetail.withArgsFormat(
            NavRoute.MovieDetail.id
        ), arguments = listOf(navArgument(NavRoute.MovieDetail.id) {
            type = NavType.StringType
        })
    ) { navBackStackEntry ->
        val args = navBackStackEntry.arguments
        val id = args?.getString(NavRoute.MovieDetail.id).toString()
        MovieDetailScreen(id = id,
            popBackStack = { navController.popBackStack() },
            navigateToVideoPlayerScreen = {
                popUpToVideoPlayerScreen(navController, id)
            })

    }
}

fun popUpToVideoPlayerScreen(navController: NavHostController, id: String) {
    navController.navigate(NavRoute.VideoPlayer.withArgs(id))
}

private fun addVideoPlayerScreen(
    navController: NavHostController, navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(
        route = NavRoute.VideoPlayer.withArgsFormat(
            NavRoute.VideoPlayer.id
        ), arguments = listOf(navArgument(NavRoute.VideoPlayer.id) {
            type = NavType.StringType
        })
    ) { navBackStackEntry ->
        val args = navBackStackEntry.arguments
        val id = args?.getString(NavRoute.VideoPlayer.id).toString()
        VideoPlayerScreen(id = id, popBackStack = { navController.popBackStack() })
    }
}

