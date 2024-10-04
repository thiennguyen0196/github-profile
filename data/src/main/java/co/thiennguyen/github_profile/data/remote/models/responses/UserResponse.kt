package co.thiennguyen.github_profile.data.remote.models.responses

import co.thiennguyen.github_profile.domain.models.User
import com.squareup.moshi.Json

class UserResponse(
    @Json(name = "login") val login: String? = null,
    @Json(name = "avatar_url") val avatarUrl: String? = null,
    @Json(name = "html_url") val htmlUrl: String? = null,
    @Json(name = "location") val location: String? = null,
    @Json(name = "followers") val followers: Int? = null,
    @Json(name = "following") val following: Int? = null,
)

fun UserResponse.toModel() = User(
    login = login.orEmpty(),
    avatarUrl = avatarUrl.orEmpty(),
    htmlUrl = htmlUrl.orEmpty(),
    location = location.orEmpty(),
    followers = followers ?: 0,
    following = following ?: 0,
)

fun List<UserResponse>.toModels() = this.map { it.toModel() }
