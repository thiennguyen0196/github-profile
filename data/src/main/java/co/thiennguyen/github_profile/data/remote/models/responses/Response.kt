package co.thiennguyen.github_profile.data.remote.models.responses

import co.thiennguyen.github_profile.domain.models.Model
import com.squareup.moshi.Json

data class Response(
    @Json(name = "id") val id: Int?
)

private fun Response.toModel() = Model(id = this.id)

fun List<Response>.toModels() = this.map { it.toModel() }
