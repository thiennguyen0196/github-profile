package co.thiennguyen.github_profile.di.modules

import co.thiennguyen.github_profile.data.local.dao.UserDao
import co.thiennguyen.github_profile.data.remote.services.ApiService
import co.thiennguyen.github_profile.data.repositories.UserRepositoryImpl
import co.thiennguyen.github_profile.domain.repositories.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideRepository(apiService: ApiService, userDao: UserDao): UserRepository =
        UserRepositoryImpl(apiService, userDao)
}
