package co.thiennguyen.github_profile.domain.usecases

import co.thiennguyen.github_profile.domain.models.User
import co.thiennguyen.github_profile.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(private val repository: UserRepository) {

    operator fun invoke(userName: String): Flow<User> {
        return repository.getUserDetail(userName)
    }
}
