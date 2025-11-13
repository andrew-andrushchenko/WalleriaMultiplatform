package com.andrii_a.walleria.ui.profile_edit

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.andrii_a.walleria.ui.common.asString
import com.andrii_a.walleria.ui.navigation.Screen
import com.andrii_a.walleria.ui.util.CollectAsOneTimeEvents
import com.andrii_a.walleria.ui.util.toast
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.editUserProfileRoute(navController: NavController) {
    composable<Screen.EditUserProfile> {
        val viewModel: EditUserProfileViewModel = koinViewModel()

        val state by viewModel.state.collectAsStateWithLifecycle()

        viewModel.profileUpdateMessageFlow.CollectAsOneTimeEvents { uiText ->
            toast(uiText.asString())
        }

        viewModel.navigationEventsChannelFlow.CollectAsOneTimeEvents { event ->
            when (event) {
                is EditUserProfileNavigationEvent.NavigateBack -> {
                    navController.navigateUp()
                }
            }
        }

        EditUserProfileScreen(
            state = state,
            onEvent = viewModel::onEvent
        )
    }
}
