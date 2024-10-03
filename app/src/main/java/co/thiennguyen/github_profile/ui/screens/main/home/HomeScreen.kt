package co.thiennguyen.github_profile.ui.screens.main.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.thiennguyen.github_profile.R
import co.thiennguyen.github_profile.extensions.collectAsEffect
import co.thiennguyen.github_profile.ui.base.BaseDestination
import co.thiennguyen.github_profile.ui.base.BaseScreen
import co.thiennguyen.github_profile.ui.models.UiModel
import co.thiennguyen.github_profile.ui.showToast
import co.thiennguyen.github_profile.ui.theme.AppTheme.dimensions
import co.thiennguyen.github_profile.ui.theme.ComposeTheme
import timber.log.Timber

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigator: (destination: BaseDestination) -> Unit,
) = BaseScreen {
    val context = LocalContext.current
    viewModel.error.collectAsEffect { e -> e.showToast(context) }
    viewModel.navigator.collectAsEffect { destination -> navigator(destination) }

    val uiModels: List<UiModel> by viewModel.uiModels.collectAsStateWithLifecycle()

    HomeScreenContent(
        title = stringResource(id = R.string.app_name),
        uiModels = uiModels
    )
}

@Composable
private fun HomeScreenContent(
    title: String,
    uiModels: List<UiModel>
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = dimensions.spacingMedium)
        )
    }
    Timber.d("Result : $uiModels")
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    ComposeTheme {
        HomeScreenContent(
            title = stringResource(id = R.string.app_name),
            uiModels = listOf(UiModel(1), UiModel(2), UiModel(3))
        )
    }
}
