package co.thiennguyen.github_profile.ui.screens.userdetail

import androidx.lifecycle.viewModelScope
import co.thiennguyen.github_profile.domain.usecases.GetUserDetailUseCase
import co.thiennguyen.github_profile.ui.base.BaseViewModel
import co.thiennguyen.github_profile.ui.models.UserUiModel
import co.thiennguyen.github_profile.ui.models.toUiModel
import co.thiennguyen.github_profile.util.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val dispatchersProvider: DispatchersProvider,
    private val getUserDetailUseCase: GetUserDetailUseCase,
) : BaseViewModel() {

    private val _user = MutableStateFlow<UserUiModel?>(null)
    val user = _user.asStateFlow()

    fun getUserDetail(userName: String) {
        getUserDetailUseCase.invoke(userName)
            .injectLoading()
            .onEach {
                _user.emit(it.toUiModel())
            }
            .flowOn(dispatchersProvider.io)
            .catch { e -> _error.emit(e) }
            .launchIn(viewModelScope)
    }
}
