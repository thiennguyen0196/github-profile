package co.thiennguyen.github_profile.ui.models

import co.thiennguyen.github_profile.domain.models.User

data class UserUiModel(
    val loginUserName: String,
    val avatarUrl: String,
    val htmlUrl: String,
    val location: String,
    val followers: Int,
    val following: Int,
)

fun User.toUiModel() = UserUiModel(
    loginUserName = this.login,
    avatarUrl = this.avatarUrl,
    htmlUrl = this.htmlUrl,
    location = this.location,
    followers = this.followers,
    following = this.following,
)

fun List<User>.toUiModels() = this.map { it.toUiModel() }