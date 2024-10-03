package co.thiennguyen.github_profile.ui.screens.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import co.thiennguyen.github_profile.ui.AppDestination
import co.thiennguyen.github_profile.ui.composable
import co.thiennguyen.github_profile.ui.screens.main.userlist.UserListScreen

fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController,
) {
    navigation(
        route = AppDestination.MainNavGraph.route,
        startDestination = MainDestination.UserList.destination
    ) {
        composable(MainDestination.UserList) {
            UserListScreen(
                onNavigateToUserDetail = {
                }
            )
        }
    }
}
