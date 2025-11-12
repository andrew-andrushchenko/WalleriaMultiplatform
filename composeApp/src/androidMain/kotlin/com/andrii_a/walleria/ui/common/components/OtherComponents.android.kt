package com.andrii_a.walleria.ui.common.components

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable

@Composable
actual fun PlatformBackHandler(enabled: Boolean, onNavigateBack: () -> Unit) {
    BackHandler(
        enabled = enabled,
        onBack = onNavigateBack
    )
}