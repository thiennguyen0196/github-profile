package co.thiennguyen.github_profile.ui.screens.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import co.thiennguyen.github_profile.ui.AppDestination
import co.thiennguyen.github_profile.ui.composable
import co.thiennguyen.github_profile.ui.navigate
import co.thiennguyen.github_profile.ui.screens.main.home.HomeScreen

fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController,
) {
    navigation(
        route = AppDestination.MainNavGraph.route,
        startDestination = MainDestination.Home.destination
    ) {
        composable(MainDestination.Home) {
            HomeScreen(
                navigator = { destination -> navController.navigate(destination) }
            )
        }
    }
}
