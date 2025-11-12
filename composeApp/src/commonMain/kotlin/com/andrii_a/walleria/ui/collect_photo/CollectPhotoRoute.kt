package com.andrii_a.walleria.ui.collect_photo

import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.andrii_a.walleria.ui.collect_photo.event.CollectPhotoNavigationEvent
import com.andrii_a.walleria.ui.common.components.PlatformBackHandler
import com.andrii_a.walleria.ui.navigation.Screen
import com.andrii_a.walleria.ui.util.COLLECT_SCREEN_RESULT_KEY
import com.andrii_a.walleria.ui.util.CollectAsOneTimeEvents
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalComposeUiApi::class)
fun NavGraphBuilder.collectPhotoRoute(navController: NavController) {
    composable<Screen.CollectPhoto> {
        val viewModel: CollectPhotoViewModel = koinViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()

        PlatformBackHandler(
            enabled = true,
            onNavigateBack = {
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set(
                        COLLECT_SCREEN_RESULT_KEY,
                        state.isCollected
                    )

                navController.popBackStack()
            }
        )

        viewModel.navigationEventsChannelFlow.CollectAsOneTimeEvents { event ->
            when (event) {
                CollectPhotoNavigationEvent.NavigateBack -> {
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set(
                            COLLECT_SCREEN_RESULT_KEY,
                            state.isCollected
                        )

                    navController.popBackStack()
                }
            }
        }

        CollectPhotoScreen(
            state = state,
            onEvent = viewModel::onEvent
        )
    }
}

