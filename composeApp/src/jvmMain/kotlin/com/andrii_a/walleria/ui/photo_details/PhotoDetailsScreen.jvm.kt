package com.andrii_a.walleria.ui.photo_details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ZoomInMap
import androidx.compose.material.icons.outlined.ZoomOutMap
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import com.andrii_a.walleria.ui.photo_details.components.ZoomableState
import com.andrii_a.walleria.ui.util.WToastDuration
import com.andrii_a.walleria.ui.util.toast
import kotlinx.coroutines.launch
import multiplatform.network.cmptoast.ToastDuration
import multiplatform.network.cmptoast.showToast
import org.jetbrains.compose.resources.stringResource
import walleriamultiplatform.composeapp.generated.resources.Res
import walleriamultiplatform.composeapp.generated.resources.login_to_collect_photo
import walleriamultiplatform.composeapp.generated.resources.login_to_like_photo

@Composable
actual fun MobileFormFactorStatusBarMutableColorDisposableEffect(currentImageScale: Float) {
}

@Composable
actual fun PlatformToolbarWrapper(
    uiState: PhotoDetailsUiState,
    zoomableState: ZoomableState,
    onEvent: (PhotoDetailsEvent) -> Unit,
) {
    val photo = uiState.photo!!

    val scope = rememberCoroutineScope()

    AnimatedVisibility(
        visible = uiState.showControls,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = Modifier
            .graphicsLayer {
                alpha = 1 - zoomableState.dismissDragProgress
            }
    ) {
        val loginToCollectPhoto = stringResource(Res.string.login_to_collect_photo)
        val loginToLikePhoto = stringResource(Res.string.login_to_like_photo)

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
                onEvent(
                    PhotoDetailsEvent.DownloadPhoto(
                        photo = photo,
                        quality = uiState.photoDownloadQuality
                    )
                )
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