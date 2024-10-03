package co.thiennguyen.github_profile.ui.screens.main.userlist

import androidx.lifecycle.viewModelScope
import co.thiennguyen.github_profile.ui.base.BaseViewModel
import co.thiennguyen.github_profile.ui.models.UserUiModel
import co.thiennguyen.github_profile.util.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    dispatchersProvider: DispatchersProvider,
) : BaseViewModel() {

    private val _users = MutableStateFlow<List<UserUiModel>>(emptyList())
    val users = _users.asStateFlow()

    init {
        // TODO: Replace this dummy data with real data
        viewModelScope.launch {
            getUsers()
        }
    }

    private suspend fun getUsers() {
        _users.emit(
            listOf(
                UserUiModel(
                    loginUserName = "David",
                    htmlUrl = "https://github.com",
                    avatarUrl = "",
                ),
                UserUiModel(
                    loginUserName = "David David David David David David David David David",
                    htmlUrl = "https://github.com.github.com.github.com.github.com",
                    avatarUrl = "",
                )
            )
        )
    }
}
