package co.thiennguyen.github_profile.data.remote.providers

import co.thiennguyen.github_profile.data.remote.services.ApiService
import retrofit2.Retrofit

object ApiServiceProvider {

    fun getApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
