package co.thiennguyen.github_profile.ui.screens.userlist

import androidx.lifecycle.viewModelScope
import co.thiennguyen.github_profile.domain.usecases.GetUsersUseCase
import co.thiennguyen.github_profile.ui.base.BaseViewModel
import co.thiennguyen.github_profile.ui.models.UserUiModel
import co.thiennguyen.github_profile.ui.models.toUiModels
import co.thiennguyen.github_profile.util.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

private const val initialPage = 0
private const val defaultPerPage = 20

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val dispatchersProvider: DispatchersProvider,
    private val getUsersUseCase: GetUsersUseCase,
) : BaseViewModel() {

    data class LoadMoreDataSet(
        var page: Int = initialPage,
        val pageSize: Int = defaultPerPage,
        var isLoading: Boolean = false,
        var isHasMore: Boolean = true
    )

    private var loadMoreDataSet = LoadMoreDataSet()

    private val _users = MutableStateFlow<List<UserUiModel>>(emptyList())
    val users = _users.asStateFlow()

    init {
        getUsers()
    }

    fun getUsers() {
        if (!loadMoreDataSet.isHasMore || loadMoreDataSet.isLoading) return
        loadMoreDataSet.isLoading = true
        getUsersUseCase.invoke(
            input = GetUsersUseCase.Input(
                page = loadMoreDataSet.page,
                perPage = loadMoreDataSet.pageSize,
            )
        )
            .injectLoading()
            .onEach { users ->
                calculateLoadMoreDataSet(users.size)
                val currentUsers = _users.value
                val totalUsers = currentUsers.plus(users.toUiModels())
                _users.emit(totalUsers)
            }
            .flowOn(dispatchersProvider.io)
            .catch { e -> _error.emit(e) }
            .launchIn(viewModelScope)
    }

    private fun calculateLoadMoreDataSet(perPageSizeResult: Int) {
        loadMoreDataSet.isLoading = false
        loadMoreDataSet.page += 1
        if (loadMoreDataSet.pageSize > perPageSizeResult) {
            loadMoreDataSet.isHasMore = false
        }
    }
}
