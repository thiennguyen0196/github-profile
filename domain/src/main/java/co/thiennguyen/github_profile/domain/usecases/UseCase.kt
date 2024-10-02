package co.thiennguyen.github_profile.domain.usecases

import co.thiennguyen.github_profile.domain.models.Model
import co.thiennguyen.github_profile.domain.repositories.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(): Flow<List<Model>> {
        return repository.getModels()
    }
}
