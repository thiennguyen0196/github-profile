package co.thiennguyen.github_profile.domain.repositories

import co.thiennguyen.github_profile.domain.models.Model
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getModels(): Flow<List<Model>>
}
