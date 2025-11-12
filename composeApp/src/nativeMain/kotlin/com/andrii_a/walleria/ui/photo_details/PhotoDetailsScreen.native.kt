package com.andrii_a.walleria.ui.photo_details

import androidx.compose.runtime.Composable
import com.andrii_a.walleria.ui.photo_details.components.ZoomableState

@Composable
actual fun MobileFormFactorStatusBarMutableColorDisposableEffect(currentImageScale: Float) {
}

@Composable
actual fun PlatformToolbarWrapper(
    uiState: PhotoDetailsUiState,
    zoomableState: ZoomableState,
    onEvent: (PhotoDetailsEvent) -> Unit,
) {
    TODO("Not yet implemented")
}