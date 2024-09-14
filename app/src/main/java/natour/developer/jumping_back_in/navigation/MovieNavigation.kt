package natour.developer.jumping_back_in.navigation

import android.telecom.Call.Details
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import natour.developer.jumping_back_in.presentation.ui.home.viewmodel.HomeScreenViewmodel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.toRoute
import natour.developer.jumping_back_in.presentation.ui.camera.screen.PictureView
import natour.developer.jumping_back_in.presentation.ui.camera.viewmodel.BlurViewmodel
import natour.developer.jumping_back_in.presentation.ui.details.screen.Details
import natour.developer.jumping_back_in.presentation.ui.home.screen.HomeScreen

@Serializable
object HomeRoute

@Serializable
data class DetailRoute(val movieId: String)

@Serializable
object CameraRoute

@Composable
fun MovieNavigation(modifier: Modifier = Modifier) {
    val moviesNavController = rememberNavController()
    val homeViewmodel = hiltViewModel<HomeScreenViewmodel>()
    val blurViewModel = hiltViewModel<BlurViewmodel>()
    NavHost(navController = moviesNavController, startDestination = HomeRoute){

        composable<HomeRoute> {
            HomeScreen(viewmodel = homeViewmodel
            ,onMovieClicked = {movieId: String ->
                    moviesNavController.navigate(DetailRoute(movieId))
                },
                onNavigateToCamera = { moviesNavController.navigate(CameraRoute) }
            )
        }

        composable<DetailRoute> {
            val movieId = it.toRoute<DetailRoute>().movieId
            Details(movieId = movieId)
        }

        composable<CameraRoute> {
            PictureView(
                viewmodel = blurViewModel
            )
        }

    }
}