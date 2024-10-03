package co.thiennguyen.github_profile.ui.screens.main.userlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.thiennguyen.github_profile.R
import co.thiennguyen.github_profile.extensions.collectAsEffect
import co.thiennguyen.github_profile.ui.base.BaseScreen
import co.thiennguyen.github_profile.ui.models.UserUiModel
import co.thiennguyen.github_profile.ui.screens.main.carditem.UserCardItem
import co.thiennguyen.github_profile.ui.showToast
import co.thiennguyen.github_profile.ui.theme.AppTheme.dimensions
import co.thiennguyen.github_profile.ui.theme.ComposeTheme

@Composable
fun UserListScreen(
    viewModel: UserListViewModel = hiltViewModel(),
    onNavigateToUserDetail: (UserUiModel) -> Unit,
) = BaseScreen {

    val context = LocalContext.current

    viewModel.error.collectAsEffect { e -> e.showToast(context) }

    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val users: List<UserUiModel> by viewModel.users.collectAsStateWithLifecycle()

    UserListScreenContent(
        users = users,
        isLoading = isLoading,
        onItemClicked = onNavigateToUserDetail,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserListScreenContent(
    users: List<UserUiModel>,
    isLoading: Boolean,
    onItemClicked: (UserUiModel) -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {},
                title = {
                    Text(
                        stringResource(id = R.string.user_list_title),
                        fontWeight = FontWeight.Bold
                    )
                },
                modifier = Modifier.background(Color.Transparent)
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(users.size) { index ->
                UserCardItem(
                    user = users[index],
                    onItemClicked = {
                        onItemClicked(users[index])
                    },
                    modifier = Modifier
                        .padding(
                            horizontal = dimensions.spacingMedium,
                            vertical = dimensions.spacingSmall,
                        )
                )
            }

            if (isLoading) {
                item {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun UserListScreenPreview() {
    ComposeTheme {
        UserListScreenContent(
            users = listOf(
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
            ),
            isLoading = false,
            onItemClicked = {},
        )
    }
}
