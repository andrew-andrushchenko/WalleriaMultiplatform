package com.andrii_a.walleria.ui.photo_details

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ZoomInMap
import androidx.compose.material.icons.outlined.ZoomOutMap
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.andrii_a.walleria.R
import com.andrii_a.walleria.ui.photo_details.components.ZoomableState
import com.andrii_a.walleria.ui.util.WToastDuration
import com.andrii_a.walleria.ui.util.toast
import kotlinx.coroutines.launch

@Composable
actual fun MobileFormFactorStatusBarMutableColorDisposableEffect(currentImageScale: Float) {
    val shouldUseDarkIcons = !isSystemInDarkTheme()
    val view = LocalView.current

    DisposableEffect(key1 = currentImageScale) {
        if (currentImageScale > 1f) {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }

        onDispose {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                shouldUseDarkIcons
        }
    }
}

@Composable
actual fun PlatformToolbarWrapper(
    uiState: PhotoDetailsUiState,
    zoomableState: ZoomableState,
    onEvent: (PhotoDetailsEvent) -> Unit,
) {
    val photo = uiState.photo!!

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { /* Unused */ }

    AnimatedVisibility(
        visible = uiState.showControls,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = Modifier
            .graphicsLayer {
                alpha = 1 - zoomableState.dismissDragProgress
            }
    ) {
        val loginToCollectPhoto = stringResource(R.string.login_to_collect_photo)
        val loginToLikePhoto = stringResource(R.string.login_to_like_photo)
        val downloadStarted = stringResource(R.string.download_started)

        PhotoActionsToolbar(
            isLikedByLoggedInUser = uiState.isLikedByLoggedInUser,
            isPhotoCollected = uiState.isCollected,
            zoomIcon = if (zoomableState.scale == 1f) Icons.Outlined.ZoomOutMap else Icons.Outlined.ZoomInMap,
            onNavigateToCollectPhoto = {
                if (uiState.isUserLoggedIn) {
                    onEvent(PhotoDetailsEvent.SelectCollectOption(photo.id))
                } else {
                    toast(
                        message = loginToCollectPhoto,
                        duration = WToastDuration.LONG
                    )
                    onEvent(PhotoDetailsEvent.RedirectToLogin)
                }
            },
            onLikeButtonClick = {
                if (uiState.isUserLoggedIn) {
                    if (uiState.isLikedByLoggedInUser) {
                        onEvent(PhotoDetailsEvent.DislikePhoto(photo.id))
                    } else {
                        onEvent(PhotoDetailsEvent.LikePhoto(photo.id))
                    }
                } else {
                    toast(
                        message = loginToLikePhoto,
                        duration = WToastDuration.LONG
                    )
                    onEvent(PhotoDetailsEvent.RedirectToLogin)
                }
            },
            onInfoButtonClick = { onEvent(PhotoDetailsEvent.ShowInfoDialog) },
            onShareButtonClick = {
                onEvent(
                    PhotoDetailsEvent.SharePhoto(
                        link = photo.links?.html,
                        description = photo.description
                    )
                )
            },
            onDownloadButtonClick = {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                    if (ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        toast(
                            message = downloadStarted,
                            duration = WToastDuration.LONG
                        )
                        onEvent(PhotoDetailsEvent.DownloadPhoto(photo))
                    } else {
                        launcher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    }
                } else {
                    toast(
                        message = downloadStarted,
                        duration = WToastDuration.LONG
                    )
                    onEvent(
                        PhotoDetailsEvent.DownloadPhoto(
                            photo = photo,
                            quality = uiState.photoDownloadQuality
                        )
                    )
                }
            },
            onZoomToFillClick = {
                scope.launch {
                    zoomableState.animateScaleTo(
                        if (zoomableState.scale >= uiState.zoomToFillCoefficient) 1f
                        else uiState.zoomToFillCoefficient
                    )
                }
            }
        )
    }
}