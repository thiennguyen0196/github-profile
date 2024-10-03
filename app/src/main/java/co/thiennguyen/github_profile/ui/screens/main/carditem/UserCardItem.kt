package co.thiennguyen.github_profile.ui.screens.main.carditem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
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
            Text(
                text = user.htmlUrl,
                style = typography.b2.copy(
                    textDecoration = TextDecoration.Underline,
                    color = colors.themeColors.secondary,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
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
            ),
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
        ),
        Params(
            userName = "David David David David David David David David David",
            htmlUrl = "https://github.com.github.com.github.com.github.com",
        )
    )

    inner class Params(
        val userName: String,
        val htmlUrl: String,
    )
}
