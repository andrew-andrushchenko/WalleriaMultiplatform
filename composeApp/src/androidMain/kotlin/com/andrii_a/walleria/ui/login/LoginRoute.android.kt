package com.andrii_a.walleria.ui.login

import android.content.Intent
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.core.util.Consumer
import com.andrii_a.walleria.data.util.Config
import com.andrii_a.walleria.ui.main.MainActivity

@Composable
actual fun OauthCodeObtainDisposableEffect(onCodeObtained: (String) -> Unit) {
    val activity = LocalActivity.current as MainActivity

    DisposableEffect(key1 = Unit) {
        val listener = Consumer<Intent> { intent ->
            intent.data?.let { uri ->
                if (uri.authority.equals(Config.AUTH_CALLBACK.substringAfterLast("/"))) {
                    uri.getQueryParameter("code")?.let { code ->
                        onCodeObtained(code)
                    }
                }
            }
        }

        activity.addOnNewIntentListener(listener)

        onDispose {
            activity.removeOnNewIntentListener(listener)
        }
    }
}