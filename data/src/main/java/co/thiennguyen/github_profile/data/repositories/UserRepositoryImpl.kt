package co.thiennguyen.github_profile.data.repositories

import co.thiennguyen.github_profile.data.extensions.flowTransform
import co.thiennguyen.github_profile.data.local.dao.UserDao
import co.thiennguyen.github_profile.data.local.entity.toEntities
import co.thiennguyen.github_profile.data.local.entity.toModels
import co.thiennguyen.github_profile.data.remote.models.responses.toModel
import co.thiennguyen.github_profile.data.remote.models.responses.toModels
import co.thiennguyen.github_profile.data.remote.services.ApiService
import co.thiennguyen.github_profile.domain.models.User
import co.thiennguyen.github_profile.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val apiService: ApiService,
    private val userDao: UserDao,
) : UserRepository {

    override fun getUsers(page: Int, perPage: Int): Flow<List<User>> = flowTransform {
        if (page == 0) {
            val localUsers = userDao.getUsers().toModels()
            if (localUsers.isNotEmpty()) {
                return@flowTransform localUsers
            }
        }

        val remoteUsers = apiService.getUsers(
            since = page * perPage,
            perPage = perPage,
        ).toModels()
        if (page == 0) {
            userDao.insertUsers(remoteUsers.toEntities())
        }
        return@flowTransform remoteUsers
    }

    override fun getUserDetail(username: String): Flow<User> = flowTransform {
        apiService.getUserDetail(
            username = username
        ).toModel()
    }
}
