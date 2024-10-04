package co.thiennguyen.github_profile.ui.models

import co.thiennguyen.github_profile.domain.models.User

data class UserUiModel(
    val loginUserName: String,
    val avatarUrl: String,
    val htmlUrl: String,
)

fun User.toUiModel() = UserUiModel(
    loginUserName = this.login,
    avatarUrl = this.avatarUrl,
    htmlUrl = this.htmlUrl,
)

fun List<User>.toUiModels() = this.map { it.toUiModel() }