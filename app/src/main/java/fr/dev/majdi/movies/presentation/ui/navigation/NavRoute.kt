package fr.dev.majdi.movies.presentation.ui.navigation

/**
 * Created by Majdi RABEH on 20/12/2023.
 * Email m.rabeh.majdi@gmail.com
 */
sealed class NavRoute(val path: String) {

    object PopularMovieList: NavRoute("popularMovieList")

    object MovieDetail: NavRoute("movieDetail") {
        val id = "id"
    }

    object VideoPlayer: NavRoute("videoPlayer") {
        val id = "id"
    }

    // build navigation path (for screen navigation)
    fun withArgs(vararg args: String): String {
        return buildString {
            append(path)
            args.forEach{ arg ->
                append("/$arg")
            }
        }
    }

    // build and setup route format (in navigation graph)
    fun withArgsFormat(vararg args: String) : String {
        return buildString {
            append(path)
            args.forEach{ arg ->
                append("/{$arg}")
            }
        }
    }
}