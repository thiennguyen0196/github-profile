package co.thiennguyen.github_profile.data.remote.models.responses

import co.thiennguyen.github_profile.domain.models.Error
import com.squareup.moshi.Json

data class ErrorResponse(
    @Json(name = "message")
    val message: String
)

internal fun ErrorResponse.toModel() = Error(message = message)
