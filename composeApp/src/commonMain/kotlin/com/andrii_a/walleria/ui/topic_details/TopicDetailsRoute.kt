package com.andrii_a.walleria.ui.topic_details

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.andrii_a.walleria.ui.navigation.Screen
import com.andrii_a.walleria.ui.util.CollectAsOneTimeEvents
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.topicDetailsRoute(navController: NavController) {
    composable<Screen.TopicDetails> {
        val viewModel: TopicDetailsViewModel = koinViewModel()

        val state by viewModel.state.collectAsStateWithLifecycle()

        //val context = LocalContext.current
        viewModel.navigationEventsChannelFlow.CollectAsOneTimeEvents { event ->
            when (event) {
                is TopicDetailsNavigationEvent.NavigateBack -> {
                    navController.navigateUp()
                }

                is TopicDetailsNavigationEvent.NavigateToChromeCustomTab -> {
                    //context.openLinkInBrowser(event.url)
                }

                is TopicDetailsNavigationEvent.NavigateToPhotoDetails -> {
                    navController.navigate(Screen.PhotoDetails(event.photoId))
                }

                is TopicDetailsNavigationEvent.NavigateToUserDetails -> {
                    navController.navigate(Screen.UserDetails(event.userNickname))
                }
            }
        }

        TopicDetailsScreen(
            state = state,
            onEvent = viewModel::onEvent,
        )
    }
}
