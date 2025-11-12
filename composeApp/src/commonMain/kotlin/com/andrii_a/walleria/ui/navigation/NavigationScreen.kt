package com.andrii_a.walleria.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.FolderSpecial
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.PhotoAlbum
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.FolderSpecial
import androidx.compose.material.icons.outlined.Photo
import androidx.compose.material.icons.outlined.PhotoAlbum
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.StringResource
import walleriamultiplatform.composeapp.generated.resources.Res
import walleriamultiplatform.composeapp.generated.resources.account
import walleriamultiplatform.composeapp.generated.resources.collections
import walleriamultiplatform.composeapp.generated.resources.photos
import walleriamultiplatform.composeapp.generated.resources.topics

enum class NavigationScreen(
    val route: Screen,
    val titleRes: StringResource,
    val iconUnselected: ImageVector,
    val iconSelected: ImageVector
) {
    Photos(
        route = Screen.Photos,
        titleRes = Res.string.photos,
        iconUnselected = Icons.Outlined.Photo,
        iconSelected = Icons.Filled.Photo
    ),
    Collections(
        route = Screen.Collections,
        titleRes = Res.string.collections,
        iconUnselected = Icons.Outlined.PhotoAlbum,
        iconSelected = Icons.Filled.PhotoAlbum
    ),
    Topics(
        route = Screen.Topics,
        titleRes = Res.string.topics,
        iconUnselected = Icons.Outlined.FolderSpecial,
        iconSelected = Icons.Filled.FolderSpecial
    ),
    AccountAndSettings(
        route = Screen.AccountAndSettings,
        titleRes = Res.string.account,
        iconUnselected = Icons.Outlined.AccountBox,
        iconSelected = Icons.Filled.AccountBox
    )
}
