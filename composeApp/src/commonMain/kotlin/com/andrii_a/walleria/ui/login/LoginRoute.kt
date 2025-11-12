package com.andrii_a.walleria.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.andrii_a.walleria.data.util.Config
import com.andrii_a.walleria.ui.navigation.Screen
import com.andrii_a.walleria.ui.util.CollectAsOneTimeEvents
import com.andrii_a.walleria.ui.util.openInBrowser
import multiplatform.network.cmptoast.showToast
import org.koin.compose.viewmodel.koinViewModel

@Composable
expect fun OauthCodeObtainDisposableEffect(
    onCodeObtained: (String) -> Unit
)

fun NavGraphBuilder.loginRoute(navController: NavController) {
    composable<Screen.Login> {
        val viewModel: LoginViewModel = koinViewModel()

        OauthCodeObtainDisposableEffect(
            onCodeObtained = { code ->
                viewModel.onEvent(LoginEvent.GetAccessToken(code))
            }
        )

        val state by viewModel.state.collectAsStateWithLifecycle()

        LaunchedEffect(key1 = state) {
            when {
                state.error != null -> {
                    showToast(message = "Login failed")
                    viewModel.onEvent(LoginEvent.DismissError)
                }

                state.isTokenObtained && !state.isUserDataSaved -> {
                    viewModel.onEvent(LoginEvent.PerformSaveUserProfile)
                }

                state.isLoggedIn -> {
                    navController.navigateUp()
                }
            }
        }

        viewModel.navigationEventFlow.CollectAsOneTimeEvents { event ->
            when (event) {
                LoginNavigationEvent.NavigateToLoginCustomTab -> {
                    openInBrowser(Config.LOGIN_URL)
                }

                LoginNavigationEvent.NavigateToJoinCustomTab -> {
                    openInBrowser(Config.JOIN_URL)
                }

                LoginNavigationEvent.NavigateBack -> {
                    navController.navigateUp()
                }
            }
        }

        LoginScreen(
            state = state,
            onEvent = viewModel::onEvent
        )
    }
}