package co.thiennguyen.github_profile.data.remote.models.responses

import co.thiennguyen.github_profile.domain.models.User
import com.squareup.moshi.Json

class UserResponse(
    @Json(name = "login") val login: String? = null,
    @Json(name = "avatar_url") val avatarUrl: String? = null,
    @Json(name = "html_url") val htmlUrl: String? = null
)

fun UserResponse.toModel() = User(
    login = login.orEmpty(),
    avatarUrl = avatarUrl.orEmpty(),
    htmlUrl = htmlUrl.orEmpty(),
)

fun List<UserResponse>.toModels() = this.map { it.toModel() }
