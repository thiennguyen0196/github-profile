package co.thiennguyen.github_profile.ui.screens.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import co.thiennguyen.github_profile.ui.AppDestination
import co.thiennguyen.github_profile.ui.composable
import co.thiennguyen.github_profile.ui.navigate
import co.thiennguyen.github_profile.ui.screens.userdetail.UserDetailScreen
import co.thiennguyen.github_profile.ui.screens.userlist.UserListScreen
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.User

private const val UserNameKey = "UserNameKey"

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
                    with(navController) {
                        navigate(MainDestination.UserDetail)
                        currentBackStackEntry?.savedStateHandle?.set(
                            UserNameKey,
                            it.loginUserName
                        )
                    }
                }
            )
        }
        composable(MainDestination.UserDetail) {
            val userName = navController.currentBackStackEntry?.savedStateHandle?.get<String>(
                UserNameKey
            )
            UserDetailScreen(
                username = userName.orEmpty(),
                onNavigateBack = {
                    navController.navigateUp()
                })
        }
    }
}
