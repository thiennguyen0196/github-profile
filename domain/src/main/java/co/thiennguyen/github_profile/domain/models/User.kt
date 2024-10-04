package co.thiennguyen.github_profile.domain.models

data class User(
    val login: String,
    val avatarUrl: String,
    val htmlUrl: String,
    val location: String,
    val followers: Int,
    val following: Int
)