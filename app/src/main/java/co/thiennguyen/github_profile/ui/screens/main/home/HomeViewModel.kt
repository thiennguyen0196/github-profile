package co.thiennguyen.github_profile.ui.screens.main.home

import androidx.lifecycle.viewModelScope
import co.thiennguyen.github_profile.domain.usecases.UseCase
import co.thiennguyen.github_profile.ui.base.BaseViewModel
import co.thiennguyen.github_profile.ui.models.UiModel
import co.thiennguyen.github_profile.ui.models.toUiModel
import co.thiennguyen.github_profile.util.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    dispatchersProvider: DispatchersProvider,
    useCase: UseCase,
) : BaseViewModel() {

    private val _uiModels = MutableStateFlow<List<UiModel>>(emptyList())
    val uiModels = _uiModels.asStateFlow()

    init {
        useCase()
            .injectLoading()
            .onEach { result ->
                val uiModels = result.map { it.toUiModel() }
                _uiModels.emit(uiModels)
            }
            .flowOn(dispatchersProvider.io)
            .catch { e -> _error.emit(e) }
            .launchIn(viewModelScope)
    }
}
