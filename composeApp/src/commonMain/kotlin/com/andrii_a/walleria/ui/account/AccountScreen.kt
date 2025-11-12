package com.andrii_a.walleria.ui.account

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialShapes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.toShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.andrii_a.walleria.domain.models.preferences.UserPrivateProfileData
import com.andrii_a.walleria.ui.theme.WalleriaTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import walleriamultiplatform.composeapp.generated.resources.Res
import walleriamultiplatform.composeapp.generated.resources.about
import walleriamultiplatform.composeapp.generated.resources.account_settings_screen
import walleriamultiplatform.composeapp.generated.resources.action_no
import walleriamultiplatform.composeapp.generated.resources.action_yes
import walleriamultiplatform.composeapp.generated.resources.add_account
import walleriamultiplatform.composeapp.generated.resources.app_name
import walleriamultiplatform.composeapp.generated.resources.creation_starts_here
import walleriamultiplatform.composeapp.generated.resources.edit_my_profile
import walleriamultiplatform.composeapp.generated.resources.ic_launcher_foreground
import walleriamultiplatform.composeapp.generated.resources.logout
import walleriamultiplatform.composeapp.generated.resources.logout_confirmation
import walleriamultiplatform.composeapp.generated.resources.settings
import walleriamultiplatform.composeapp.generated.resources.user_full_name_with_nickname_formatted
import walleriamultiplatform.composeapp.generated.resources.user_profile_edit
import walleriamultiplatform.composeapp.generated.resources.user_profile_image
import walleriamultiplatform.composeapp.generated.resources.user_profile_view

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    state: AccountScreenUiState,
    onEvent: (AccountScreenEvent) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(Res.string.account_settings_screen))
                },
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .widthIn(min = 250.dp, max = 400.dp)
                    .animateContentSize()
                    .align(Alignment.TopCenter)
                    .verticalScroll(rememberScrollState())
            ) {
                if (state.isUserLoggedIn) {
                    LoggedInHeader(
                        userPrivateProfileData = state.userPrivateProfileData!!,
                        showConfirmation = state.shouldShowLogoutConfirmation,
                        onShowLogoutConfirmation = {
                            onEvent(AccountScreenEvent.ToggleLogoutConfirmation(true))
                        },
                        onDismissLogout = {
                            onEvent(AccountScreenEvent.ToggleLogoutConfirmation(false))
                        },
                        navigateToViewProfileScreen = {
                            onEvent(AccountScreenEvent.OpenViewProfileScreen(state.userPrivateProfileData.nickname))
                        },
                        navigateToEditProfileScreen = {
                            onEvent(AccountScreenEvent.OpenEditProfileScreen)
                        },
                        onLogout = { onEvent(AccountScreenEvent.Logout) },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                } else {
                    LoggedOutHeader(
                        navigateToLoginScreen = { onEvent(AccountScreenEvent.OpenLoginScreen) },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedButton(
                    onClick = { onEvent(AccountScreenEvent.OpenSettingsScreen) },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = stringResource(Res.string.settings)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(text = stringResource(Res.string.settings))
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedButton(
                    onClick = { onEvent(AccountScreenEvent.OpenAboutScreen) },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = stringResource(Res.string.about)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(text = stringResource(Res.string.about))
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun LoggedOutHeader(
    navigateToLoginScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Surface(
            color = MaterialTheme.colorScheme.surfaceContainer,
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(128.dp)
        ) {
            Box(
                modifier = Modifier
            ) {
                Surface(
                    shape = CircleShape,
                    tonalElevation = 8.dp,
                    modifier = Modifier.graphicsLayer {
                        translationX = -100f
                        alpha = 0.4f
                    }
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_launcher_foreground),
                        contentDescription = stringResource(Res.string.app_name),
                        tint = MaterialTheme.colorScheme.surfaceTint,
                        modifier = Modifier
                            .size(128.dp)
                            .scale(1.3f)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.align(Alignment.Center),
                ) {
                    Text(
                        text = stringResource(Res.string.app_name),
                        style = MaterialTheme.typography.headlineSmall,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = stringResource(Res.string.creation_starts_here),
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = navigateToLoginScreen,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.AddCircleOutline,
                contentDescription = stringResource(Res.string.add_account)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(text = stringResource(Res.string.add_account))
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun LoggedInHeader(
    showConfirmation: Boolean,
    onShowLogoutConfirmation: () -> Unit,
    onDismissLogout: () -> Unit,
    userPrivateProfileData: UserPrivateProfileData,
    navigateToViewProfileScreen: () -> Unit,
    navigateToEditProfileScreen: () -> Unit,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        tonalElevation = 1.dp,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalPlatformContext.current)
                    .data(userPrivateProfileData.profilePhotoUrl)
                    .crossfade(true)
                    //.placeholder(Color.LTGRAY.toDrawable())
                    .build(),
                contentDescription = stringResource(Res.string.user_profile_image),
                modifier = Modifier
                    .size(72.dp)
                    .clip(MaterialShapes.Pill.toShape())
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(
                    Res.string.user_full_name_with_nickname_formatted,
                    userPrivateProfileData.firstName,
                    userPrivateProfileData.lastName,
                    userPrivateProfileData.nickname
                ),
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = userPrivateProfileData.email,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(16.dp))

            AnimatedContent(
                targetState = showConfirmation,
                label = "",
                modifier = Modifier.fillMaxWidth()
            ) {
                if (it) {
                    LogoutConfirmationRow(
                        onConfirm = onLogout,
                        onDismiss = onDismissLogout,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                } else {
                    ProfileActionRow(
                        navigateToViewProfileScreen = navigateToViewProfileScreen,
                        navigateToEditProfileScreen = navigateToEditProfileScreen,
                        onLogoutClick = onShowLogoutConfirmation,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ProfileActionRow(
    navigateToViewProfileScreen: () -> Unit,
    navigateToEditProfileScreen: () -> Unit,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(
        horizontalArrangement = Arrangement.Center,
        maxLines = 3,
        modifier = modifier
    ) {
        TextButton(
            onClick = navigateToViewProfileScreen,
            shape = RoundedCornerShape(16.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.RemoveRedEye,
                contentDescription = stringResource(Res.string.user_profile_view)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = stringResource(Res.string.user_profile_view),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        TextButton(
            onClick = navigateToEditProfileScreen,
            shape = RoundedCornerShape(16.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Edit,
                contentDescription = stringResource(Res.string.edit_my_profile)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = stringResource(Res.string.user_profile_edit),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        TextButton(
            onClick = onLogoutClick,
            shape = RoundedCornerShape(16.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.Logout,
                contentDescription = stringResource(Res.string.logout)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = stringResource(Res.string.logout),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun LogoutConfirmationRow(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(modifier = modifier) {
        val (confirmationText, confirmButton, dismissButton) = createRefs()

        Text(
            text = stringResource(Res.string.logout_confirmation),
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(confirmationText) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(confirmButton.start)
                width = Dimension.fillToConstraints
            }
        )

        TextButton(
            onClick = onConfirm,
            modifier = Modifier.constrainAs(confirmButton) {
                top.linkTo(dismissButton.top)
                bottom.linkTo(dismissButton.bottom)
                end.linkTo(dismissButton.start)
            }
        ) {
            Text(
                text = stringResource(Res.string.action_yes),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Button(
            onClick = onDismiss,
            modifier = Modifier.constrainAs(dismissButton) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            }
        ) {
            Text(
                stringResource(Res.string.action_no),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    WalleriaTheme {
        val state = AccountScreenUiState(
            isUserLoggedIn = true,
            userPrivateProfileData = UserPrivateProfileData(
                nickname = "john",
                firstName = "John",
                lastName = "Smith",
                email = "john.smith@example.com"
            )
        )

        Surface {
            ProfileScreen(
                state = state,
                onEvent = {}
            )
        }
    }
}