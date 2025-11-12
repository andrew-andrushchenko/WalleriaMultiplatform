package com.andrii_a.walleria.ui.user_details

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.andrii_a.walleria.ui.navigation.Screen
import com.andrii_a.walleria.ui.util.CollectAsOneTimeEvents
import com.andrii_a.walleria.ui.util.openInBrowser
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.userDetailsRoute(navController: NavController) {
    composable<Screen.UserDetails> {
        val viewModel: UserDetailsViewModel = koinViewModel()

        val state by viewModel.state.collectAsStateWithLifecycle()

        viewModel.navigationEventsChannelFlow.CollectAsOneTimeEvents { event ->
            when (event) {
                is UserDetailsNavigationEvent.NavigateBack -> {
                    navController.navigateUp()
                }

                is UserDetailsNavigationEvent.NavigateToPhotoDetailsScreen -> {
                    navController.navigate(Screen.PhotoDetails(event.photoId))
                }

                is UserDetailsNavigationEvent.NavigateToCollectionDetails -> {
                    navController.navigate(Screen.CollectionDetails(event.collectionId))
                }

                is UserDetailsNavigationEvent.NavigateToUserDetails -> {
                    navController.navigate(Screen.UserDetails(event.userNickname))
                }

                is UserDetailsNavigationEvent.NavigateToSearchScreen -> {
                    navController.navigate(Screen.Search(event.query))
                }

                is UserDetailsNavigationEvent.NavigateToEditProfile -> {
                    navController.navigate(Screen.EditUserProfile)
                }

                is UserDetailsNavigationEvent.NavigateToUserProfileInChromeTab -> {
                    openInBrowser("https://unsplash.com/@${event.userNickname}")
                }

                is UserDetailsNavigationEvent.NavigateToChromeCustomTab -> {
                    openInBrowser(event.url)
                }

                is UserDetailsNavigationEvent.NavigateToInstagramApp -> {
                    openInBrowser("https://instagram.com/${event.instagramNickname}")
                }

                is UserDetailsNavigationEvent.NavigateToTwitterApp -> {
                    openInBrowser("https://twitter.com/${event.twitterNickname}")
                }
            }
        }

        UserDetailsScreen(
            state = state,
            onEvent = viewModel::onEvent
        )
    }
}
