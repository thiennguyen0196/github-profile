package co.thiennguyen.github_profile.domain.exceptions

import co.thiennguyen.github_profile.domain.models.Error

object NoConnectivityException : RuntimeException()

data class ApiException(
    val error: Error?,
    val httpCode: Int,
    val httpMessage: String?
) : RuntimeException()
