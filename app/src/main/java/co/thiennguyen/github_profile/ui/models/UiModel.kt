package co.thiennguyen.github_profile.ui.models

import co.thiennguyen.github_profile.domain.models.Model

data class UiModel(
    val id: Int
)

fun Model.toUiModel() = UiModel(id = id ?: -1)
