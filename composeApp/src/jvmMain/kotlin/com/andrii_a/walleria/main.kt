package com.andrii_a.walleria

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.andrii_a.walleria.ui.util.shouldUseDarkTheme
import com.andrii_a.walleria.di.initKoin
import com.andrii_a.walleria.domain.repository.LocalPreferencesRepository
import com.andrii_a.walleria.ui.main.WalleriaApp
import multiplatform.network.cmptoast.ToastHost
import org.koin.compose.koinInject

fun main() = application {
    initKoin()
    val localPreferencesRepository: LocalPreferencesRepository = koinInject()

    Window(
        onCloseRequest = ::exitApplication,
        title = "Walleria Multiplatform",
    ) {
        WalleriaApp(darkTheme = localPreferencesRepository.shouldUseDarkTheme)
        ToastHost()
    }
}