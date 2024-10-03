package co.thiennguyen.github_profile.di.modules

import co.thiennguyen.github_profile.data.remote.services.ApiService
import co.thiennguyen.github_profile.data.repositories.RepositoryImpl
import co.thiennguyen.github_profile.domain.repositories.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideRepository(apiService: ApiService): Repository = RepositoryImpl(apiService)
}
