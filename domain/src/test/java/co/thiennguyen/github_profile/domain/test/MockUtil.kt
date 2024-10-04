package co.thiennguyen.github_profile.domain.test

import co.thiennguyen.github_profile.domain.models.User

object MockUtil {

    val users = listOf(
        User(
            login = "login",
            avatarUrl = "avatarUrl",
            htmlUrl = "htmlUrl",
            location = "location",
            followers = 2,
            following = 2,
        )
    )
}
