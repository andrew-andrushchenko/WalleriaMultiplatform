package com.andrii_a.walleria.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.andrii_a.walleria.data.remote.services.startRedirectServer

@Composable
actual fun OauthCodeObtainDisposableEffect(onCodeObtained: (String) -> Unit) {
    DisposableEffect(key1 = Unit) {
        val server = startRedirectServer(onCodeObtained = onCodeObtained)

        onDispose {
            server.stop()
        }
    }
}