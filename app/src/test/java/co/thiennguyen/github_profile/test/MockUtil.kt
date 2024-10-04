package co.thiennguyen.github_profile.test

import co.thiennguyen.github_profile.domain.models.User

object MockUtil {

    val users = listOf(
        User(
            login = "login",
            avatarUrl = "avatarUrl",
            htmlUrl = "htmlUrl",
            followers = 2,
            following = 2,
            location = "location",
        )
    )
}
