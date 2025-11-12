package com.andrii_a.walleria.ui.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.andrii_a.walleria.ui.common.components.WLoadingIndicator
import com.andrii_a.walleria.ui.theme.WalleriaLogoTextStyle
import com.andrii_a.walleria.ui.theme.WalleriaTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import walleriamultiplatform.composeapp.generated.resources.Res
import walleriamultiplatform.composeapp.generated.resources.app_name
import walleriamultiplatform.composeapp.generated.resources.continue_as_a_guest
import walleriamultiplatform.composeapp.generated.resources.login
import walleriamultiplatform.composeapp.generated.resources.sign_up

@Composable
fun LoginScreen(
    state: LoginUiState,
    onEvent: (LoginEvent) -> Unit
) {
    Scaffold(
        bottomBar = {
            Box(modifier = Modifier.fillMaxWidth()) {
                TextButton(
                    onClick = { onEvent(LoginEvent.GoBack) },
                    enabled = !state.isLoading,
                    modifier = Modifier
                        .padding(bottom = 24.dp)
                        .align(Alignment.Center)
                ) {
                    Text(
                        text = stringResource(Res.string.continue_as_a_guest),
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .align(Alignment.Center)
                    .widthIn(min = 250.dp, max = 350.dp)
                    .verticalScroll(rememberScrollState())
                    .animateContentSize()
            ) {
                Text(
                    text = stringResource(Res.string.app_name),
                    style = WalleriaLogoTextStyle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { onEvent(LoginEvent.PerformLogin) },
                    shape = RoundedCornerShape(16.dp),
                    enabled = !state.isLoading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                ) {
                    Text(text = stringResource(Res.string.login))
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedButton(
                    onClick = { onEvent(LoginEvent.PerformJoin) },
                    shape = RoundedCornerShape(16.dp),
                    enabled = !state.isLoading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                ) {
                    Text(text = stringResource(Res.string.sign_up))
                }

                AnimatedVisibility(visible = state.isLoading) {
                    Spacer(modifier = Modifier.height(8.dp))

                    WLoadingIndicator()
                }
            }
        }
    }
}


@Preview
@Composable
fun LoginScreenPreview() {
    WalleriaTheme {
        LoginScreen(
            state = LoginUiState(isLoading = false),
            onEvent = {}
        )
    }
}
