package co.thiennguyen.github_profile.domain.repositories

import co.thiennguyen.github_profile.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(page: Int, perPage: Int): Flow<List<User>>
}
