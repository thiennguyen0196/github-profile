package co.thiennguyen.github_profile.ui.screens.main

import co.thiennguyen.github_profile.ui.base.BaseDestination

sealed class MainDestination {

    object UserList : BaseDestination("user_list")

    object UserDetail : BaseDestination("user_detail")
}
