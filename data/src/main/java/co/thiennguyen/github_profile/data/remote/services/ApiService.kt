package co.thiennguyen.github_profile.data.remote.services

import co.thiennguyen.github_profile.data.remote.models.responses.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUsers(
        @Query("since") since: Int,
        @Query("per_page") perPage: Int,
    ): List<UserResponse>

    @GET("users/{login_username}")
    suspend fun getUserDetail(
        @Path("login_username") username: String,
    ): UserResponse
}
