package co.thiennguyen.github_profile.data.remote.services

import co.thiennguyen.github_profile.data.remote.models.responses.Response
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getResponses(): List<Response>
}
