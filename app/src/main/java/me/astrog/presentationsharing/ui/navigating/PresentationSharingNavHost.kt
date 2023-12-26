package me.astrog.presentationsharing.ui.navigating

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import me.astrog.presentationsharing.ui.composable.upload.RoomUploadScreen


private const val TAG = "PresentationSharingNavHost"


@Composable
fun PresentationSharingNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = RoomScreens.UploadFile.route) {
        composable(RoomScreens.UploadFile.route) {
            RoomUploadScreen(
                onAcceptPptx = { pptxUri ->
                    navController.navigate(
                        route = "${RoomScreens.ShareFile.routeWithoutArgs}/${pptxUri}"
                    )
                },
                viewModel = hiltViewModel(),
            )
        }
        composable(RoomScreens.ShareFile.route) { navBackStackEntry ->
            val pptxUri = navBackStackEntry.arguments?.getString("pptxUri")
            if (pptxUri == null) {
                Log.e(TAG, "navigate to \"${RoomScreens.ShareFile.route}\" with null argument")
                navController.navigate(RoomScreens.UploadFile.route)
            }
        }
    }
}