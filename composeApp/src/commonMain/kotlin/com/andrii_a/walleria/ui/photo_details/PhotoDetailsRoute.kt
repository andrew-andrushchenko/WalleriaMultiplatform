package com.andrii_a.walleria.ui.photo_details

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.andrii_a.walleria.ui.navigation.Screen
import com.andrii_a.walleria.ui.util.COLLECT_SCREEN_RESULT_KEY
import com.andrii_a.walleria.ui.util.CollectAsOneTimeEvents
import com.andrii_a.walleria.ui.util.openInBrowser
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.photoDetailsRoute(navController: NavController) {
    composable<Screen.PhotoDetails> {
        val viewModel: PhotoDetailsViewModel = koinViewModel()

        val state by viewModel.state.collectAsStateWithLifecycle()

        val collectResult = navController.currentBackStackEntry
            ?.savedStateHandle
            ?.getStateFlow(
                COLLECT_SCREEN_RESULT_KEY,
                state.isCollected
            )
            ?.collectAsStateWithLifecycle()

        LaunchedEffect(key1 = collectResult?.value) {
            viewModel.onEvent(
                if (collectResult?.value == true) {
                    PhotoDetailsEvent.MakeCollected
                } else {
                    PhotoDetailsEvent.MakeDropped
                }
            )
        }

        viewModel.navigationEventsChannelFlow.CollectAsOneTimeEvents { event ->
            when (event) {
                is PhotoDetailsNavigationEvent.NavigateBack -> {
                    navController.navigateUp()
                }

                is PhotoDetailsNavigationEvent.NavigateToCollectPhoto -> {
                    navController.navigate(Screen.CollectPhoto(event.photoId))
                }

                is PhotoDetailsNavigationEvent.NavigateToCollectionDetails -> {
                    navController.navigate(Screen.CollectionDetails(event.collectionId))
                }

                is PhotoDetailsNavigationEvent.NavigateToSearch -> {
                    navController.navigate(Screen.Search(event.query))
                }

                is PhotoDetailsNavigationEvent.NavigateToUserDetails -> {
                    navController.navigate(Screen.UserDetails(event.userNickname))
                }

                is PhotoDetailsNavigationEvent.NavigateToChromeCustomTab -> {
                    openInBrowser(event.url)
                }

                is PhotoDetailsNavigationEvent.NavigateToLogin -> {
                    navController.navigate(Screen.Login)
                }

                is PhotoDetailsNavigationEvent.NavigateToShareDialog -> {
                    /*context.sharePhoto(
                        photoLink = event.link,
                        photoDescription = event.description
                    )*/
                }
            }
        }

        PhotoDetailsScreen(
            state = state,
            onEvent = viewModel::onEvent
        )
    }
}

