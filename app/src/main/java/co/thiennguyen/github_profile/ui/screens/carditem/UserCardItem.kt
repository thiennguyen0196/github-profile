package co.thiennguyen.github_profile.ui.screens.carditem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import co.thiennguyen.github_profile.ui.models.UserUiModel
import co.thiennguyen.github_profile.ui.theme.AppTheme.colors
import co.thiennguyen.github_profile.ui.theme.AppTheme.dimensions
import co.thiennguyen.github_profile.ui.theme.AppTheme.typography
import co.thiennguyen.github_profile.ui.theme.ComposeTheme
import coil.compose.AsyncImage

@Composable
fun UserCardItem(
    user: UserUiModel,
    onItemClicked: () -> Unit,
    modifier: Modifier = Modifier,
    showLocation: Boolean = false,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .shadow(dimensions.shadowMedium, RoundedCornerShape(dimensions.cornerMedium))
            .clip(RoundedCornerShape(dimensions.cornerMedium))
            .background(colors.themeColors.surface)
            .clickable { onItemClicked() }
            .padding(vertical = dimensions.spacingSmall),
    ) {
        AsyncImage(
            model = user.avatarUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(horizontal = dimensions.spacingSmall)
                .size(dimensions.imageSizeLarge)
                .background(
                    color = colors.themeColors.primary,
                    shape = RoundedCornerShape(dimensions.cornerSmall)
                )
                .padding(dimensions.spacingXSmall)
                .clip(CircleShape)
        )
        Column(
            modifier = Modifier.padding(
                end = dimensions.spacingSmall
            )
        ) {
            Text(
                text = user.loginUserName,
                style = typography.t2,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            HorizontalDivider(
                color = colors.themeColors.primary,
                thickness = dimensions.dividerThickness,
                modifier = Modifier.padding(vertical = dimensions.spacingSmall)
            )
            if (!showLocation) {
                Text(
                    text = user.htmlUrl,
                    style = typography.b2.copy(
                        textDecoration = TextDecoration.Underline,
                        color = colors.themeColors.secondary,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            } else if (user.location.isNotBlank()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = user.location,
                        style = typography.b2,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun UserCardItemPreview(
    @PreviewParameter(UserCardItemParameterProvider::class)
    params: UserCardItemParameterProvider.Params,
) {
    ComposeTheme {
        UserCardItem(
            user = UserUiModel(
                loginUserName = params.userName,
                avatarUrl = "",
                htmlUrl = params.htmlUrl,
                followers = params.followers,
                following = params.following,
                location = params.location
            ),
            showLocation = params.showLocation,
            onItemClicked = {},
        )
    }
}

internal class UserCardItemParameterProvider :
    PreviewParameterProvider<UserCardItemParameterProvider.Params> {
    override val values = sequenceOf(
        Params(
            userName = "David",
            htmlUrl = "https://github.com",
            followers = 2,
            following = 2,
            location = "",
            showLocation = false,
        ),
        Params(
            userName = "David David David David David David David David David",
            htmlUrl = "https://github.com.github.com.github.com.github.com",
            followers = 2,
            following = 2,
            location = "",
            showLocation = false,
        ),
        Params(
            userName = "David",
            htmlUrl = "https://github.com",
            followers = 2,
            following = 2,
            location = "Vietnam",
            showLocation = true,
        ),
        Params(
            userName = "David David David David David David David David David",
            htmlUrl = "https://github.com.github.com.github.com.github.com",
            followers = 2,
            following = 2,
            location = "Vietnam Vietnam Vietnam Vietnam Vietnam Vietnam Vietnam",
            showLocation = true,
        )
    )

    inner class Params(
        val userName: String,
        val htmlUrl: String,
        val followers: Int,
        val following: Int,
        val location: String,
        val showLocation: Boolean,
    )
}
