package com.andrii_a.walleria.ui.settings

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.andrii_a.walleria.ui.navigation.Screen
import com.andrii_a.walleria.ui.util.CollectAsOneTimeEvents
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.settingsRoute(navController: NavController) {
    composable<Screen.Settings> {
        val viewModel: SettingsViewModel = koinViewModel()

        viewModel.navigationEventFlow.CollectAsOneTimeEvents { event ->
            when (event) {
                is SettingsNavigationEvent.NavigateBack -> {
                    navController.navigateUp()
                }
            }
        }

        val state by viewModel.state.collectAsStateWithLifecycle()

        SettingsScreen(
            state = state,
            onEvent = viewModel::onEvent,
        )
    }
}
