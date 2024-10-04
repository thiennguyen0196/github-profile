package co.thiennguyen.github_profile.domain.usecases

import co.thiennguyen.github_profile.domain.models.User
import co.thiennguyen.github_profile.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val repository: UserRepository) {

    data class Input(
        val page: Int,
        val perPage: Int,
    )

    operator fun invoke(input: Input): Flow<List<User>> {
        return repository.getUsers(
            page = input.page,
            perPage = input.perPage,
        )
    }
}
