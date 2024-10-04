package co.thiennguyen.github_profile.ui.screens.userdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.thiennguyen.github_profile.R
import co.thiennguyen.github_profile.extensions.collectAsEffect
import co.thiennguyen.github_profile.ui.models.UserUiModel
import co.thiennguyen.github_profile.ui.screens.carditem.UserCardItem
import co.thiennguyen.github_profile.ui.showToast
import co.thiennguyen.github_profile.ui.theme.AppTheme.colors
import co.thiennguyen.github_profile.ui.theme.AppTheme.dimensions
import co.thiennguyen.github_profile.ui.theme.AppTheme.typography
import co.thiennguyen.github_profile.ui.theme.ComposeTheme

@Composable
fun UserDetailScreen(
    username: String,
    onNavigateBack: () -> Unit,
    viewModel: UserDetailViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    viewModel.error.collectAsEffect { e -> e.showToast(context) }

    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val user by viewModel.user.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getUserDetail(username)
    }

    UserDetailScreenContent(
        user = user,
        isLoading = isLoading,
        onBackIconClicked = onNavigateBack,
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreenContent(
    user: UserUiModel?,
    isLoading: Boolean,
    onBackIconClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(id = R.string.user_detail_title),
                        style = typography.t1
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackIconClicked) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                modifier = Modifier.background(colors.themeColors.surface),
            )
        }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = dimensions.spacingMedium),
        ) {
            if (user == null) return@Scaffold
            UserCardItem(
                user = user,
                onItemClicked = {},
                showLocation = true,
                modifier = Modifier.padding(bottom = dimensions.spacingXLarge)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensions.spacingXLarge),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = null,
                        modifier = Modifier
                            .size(dimensions.imageSizeMedium)
                            .background(color = colors.themeColors.primary, shape = CircleShape)
                            .padding(dimensions.spacingMedium)
                    )
                    Text(
                        text = "${user.followers}+",
                        style = typography.b1,
                        modifier = Modifier.padding(dimensions.spacingSmall
                        )
                        )
                    Text(
                        text = stringResource(id = R.string.user_detail_followers),
                        style = typography.b2,
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = null,
                        modifier = Modifier
                            .size(dimensions.imageSizeMedium)
                            .background(color = colors.themeColors.primary, shape = CircleShape)
                            .padding(dimensions.spacingMedium)
                    )
                    Text(text = "${user.following}+", style = typography.b1)
                    Text(
                        text = stringResource(id = R.string.user_detail_following),
                        style = typography.b2,
                    )
                }
            }
            Text(
                text = stringResource(id = R.string.user_detail_blog),
                style = typography.t2,
                modifier = Modifier.padding(bottom = dimensions.spacingSmall)
            )
            Text(
                text = user.htmlUrl,
                style = typography.b2,
            )
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.padding(dimensions.spacingXLarge))
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun UserListScreenPreview() {
    ComposeTheme {
        UserDetailScreenContent(
            user = UserUiModel(
                loginUserName = "David",
                htmlUrl = "https://github.com",
                avatarUrl = "",
                location = "location",
                following = 2,
                followers = 2,
            ),
            isLoading = false,
            onBackIconClicked = {},
        )
    }
}