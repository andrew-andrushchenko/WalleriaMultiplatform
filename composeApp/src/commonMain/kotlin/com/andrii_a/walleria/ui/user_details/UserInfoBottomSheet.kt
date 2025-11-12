package com.andrii_a.walleria.ui.user_details

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.andrii_a.walleria.domain.models.common.Tag
import com.andrii_a.walleria.domain.models.user.User
import com.andrii_a.walleria.domain.models.user.UserSocialMediaLinks
import com.andrii_a.walleria.domain.models.user.UserTags
import com.andrii_a.walleria.ui.common.SearchQuery
import com.andrii_a.walleria.ui.common.components.TagsRow
import com.andrii_a.walleria.ui.theme.WalleriaTheme
import com.andrii_a.walleria.ui.util.abbreviatedNumberString
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import walleriamultiplatform.composeapp.generated.resources.Res
import walleriamultiplatform.composeapp.generated.resources.collections
import walleriamultiplatform.composeapp.generated.resources.downloads
import walleriamultiplatform.composeapp.generated.resources.followers
import walleriamultiplatform.composeapp.generated.resources.following
import walleriamultiplatform.composeapp.generated.resources.photos
import walleriamultiplatform.composeapp.generated.resources.total_likes

@Composable
fun UserInfoBottomSheet(
    user: User,
    navigateToSearch: (SearchQuery) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        UserStatistics(
            user = user,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        HorizontalDivider(modifier = Modifier.padding(16.dp))

        user.bio?.let {
            var isExpanded by rememberSaveable { mutableStateOf(false) }

            Text(
                text = it,
                style = MaterialTheme.typography.titleSmall,
                maxLines = if (isExpanded) Int.MAX_VALUE else 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isExpanded = !isExpanded }
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterHorizontally)
                    .animateContentSize()
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        user.tags?.custom?.let {
            TagsRow(
                tags = it,
                onTagClicked = { query ->
                    navigateToSearch(query)
                },
                contentPadding = PaddingValues(horizontal = 16.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun UserStatsItem(
    titleRes: StringResource,
    value: Long,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(titleRes),
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = value.abbreviatedNumberString,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun UserStatistics(
    user: User,
    modifier: Modifier = Modifier
) {
    // Pairs of (title, value)
    val statsGridItems = listOf(
        Pair(Res.string.downloads, user.downloads),
        Pair(Res.string.photos, user.totalPhotos),
        Pair(Res.string.total_likes, user.totalLikes),
        Pair(Res.string.collections, user.totalCollections),
        Pair(Res.string.followers, user.followersCount),
        Pair(Res.string.following, user.followingCount)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.height(90.dp)
    ) {
        items(statsGridItems) { item ->
            UserStatsItem(titleRes = item.first, value = item.second)
        }
    }
}

@Preview
@Composable
fun UserInfoBottomSheetPreview() {
    WalleriaTheme {
        val tags = "Lorem ipsum dolor sit amet another very interesting text"
            .split(" ")
            .map { Tag(it) }

        val user = User(
            id = "",
            username = "ABC",
            firstName = "John",
            lastName = "Smith",
            bio = "Lorem ipsum dolor sit amet".repeat(10),
            location = "San Francisco, California, USA",
            totalLikes = 100,
            totalPhotos = 100,
            totalCollections = 100,
            followersCount = 100_000,
            followingCount = 56,
            downloads = 99_000,
            profileImage = null,
            social = UserSocialMediaLinks(
                instagramUsername = "abc",
                portfolioUrl = "abc",
                twitterUsername = "abc",
                paypalEmail = "abc"
            ),
            tags = UserTags(tags),
            photos = null
        )

        Surface {
            UserInfoBottomSheet(user = user, navigateToSearch = {})
        }
    }
}