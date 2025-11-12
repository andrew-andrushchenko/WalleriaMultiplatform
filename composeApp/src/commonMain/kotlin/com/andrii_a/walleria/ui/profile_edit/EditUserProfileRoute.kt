package com.andrii_a.walleria.ui.profile_edit

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.andrii_a.walleria.ui.navigation.Screen
import com.andrii_a.walleria.ui.util.CollectAsOneTimeEvents
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.editUserProfileRoute(navController: NavController) {
    composable<Screen.EditUserProfile> {
        val viewModel: EditUserProfileViewModel = koinViewModel()

        val state by viewModel.state.collectAsStateWithLifecycle()

        //val context = LocalContext.current
        viewModel.profileUpdateMessageFlow.CollectAsOneTimeEvents { uiText ->
            //context.toast(uiText.asString(context))
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
