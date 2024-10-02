package co.thiennguyen.github_profile.data.repositories

import co.thiennguyen.github_profile.data.extensions.flowTransform
import co.thiennguyen.github_profile.data.remote.models.responses.toModels
import co.thiennguyen.github_profile.data.remote.services.ApiService
import co.thiennguyen.github_profile.domain.models.Model
import co.thiennguyen.github_profile.domain.repositories.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryImpl constructor(
    private val apiService: ApiService
) : Repository {

    override fun getModels(): Flow<List<Model>> = flowTransform {
        apiService.getResponses().toModels()
    }
}
