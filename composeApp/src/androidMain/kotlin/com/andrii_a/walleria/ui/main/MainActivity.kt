package com.andrii_a.walleria.ui.main

import android.content.ComponentName
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.browser.customtabs.CustomTabsCallback
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsService
import androidx.browser.customtabs.CustomTabsServiceConnection
import androidx.browser.customtabs.CustomTabsSession
import androidx.core.net.toUri
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.andrii_a.walleria.ui.util.shouldUseDarkTheme
import com.andrii_a.walleria.data.util.Config
import com.andrii_a.walleria.domain.repository.LocalPreferencesRepository
import com.andrii_a.walleria.ui.util.CustomTabsHelper
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private var customTabsClient: CustomTabsClient? = null
    private var customTabsSession: CustomTabsSession? = null
    private var shouldUnbindCustomTabService = false

    private val customTabsServiceConnection = object : CustomTabsServiceConnection() {
        override fun onCustomTabsServiceConnected(
            name: ComponentName,
            client: CustomTabsClient
        ) {
            customTabsClient = client
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            customTabsClient = null
        }
    }

    private val localPreferencesRepository by inject<LocalPreferencesRepository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()

        setupCustomTabs()

        setContent {
            WalleriaApp(darkTheme = localPreferencesRepository.shouldUseDarkTheme)
        }
    }

    private fun setupCustomTabs() {
        CustomTabsHelper.getPackageNameToUse(this)?.let { customTabsPackageName ->
            if (CustomTabsClient.bindCustomTabsService(
                    this,
                    customTabsPackageName,
                    customTabsServiceConnection
                )
            ) {
                shouldUnbindCustomTabService = true
                customTabsClient?.warmup(0)
                customTabsSession = customTabsClient?.newSession(CustomTabsCallback())?.apply {
                    mayLaunchUrl(
                        Config.LOGIN_URL.toUri(),
                        null,
                        mutableListOf(
                            Bundle().apply {
                                putParcelable(
                                    CustomTabsService.KEY_URL,
                                    Config.JOIN_URL.toUri()
                                )
                            }
                        )
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (shouldUnbindCustomTabService) {
            unbindService(customTabsServiceConnection)
            shouldUnbindCustomTabService = false
        }
        customTabsClient = null
        customTabsSession = null
    }
}
