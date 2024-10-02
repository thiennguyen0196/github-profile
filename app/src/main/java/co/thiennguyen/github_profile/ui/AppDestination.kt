package co.thiennguyen.github_profile.ui

import co.thiennguyen.github_profile.ui.base.BaseDestination

sealed class AppDestination {

    object RootNavGraph : BaseDestination("rootNavGraph")

    object MainNavGraph : BaseDestination("mainNavGraph")
}
