package com.andrii_a.walleria.ui.photos

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.andrii_a.walleria.domain.PhotoListDisplayOrder
import com.andrii_a.walleria.ui.common.components.OptionsMenu
import com.andrii_a.walleria.ui.common.components.PhotosGridContent
import com.andrii_a.walleria.ui.theme.WalleriaTheme
import com.andrii_a.walleria.ui.util.titleRes
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import walleriamultiplatform.composeapp.generated.resources.Res
import walleriamultiplatform.composeapp.generated.resources.list_order_menu
import walleriamultiplatform.composeapp.generated.resources.photos_title_template
import walleriamultiplatform.composeapp.generated.resources.search

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotosScreen(
    state: PhotosUiState,
    onEvent: (PhotosEvent) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val colors = TopAppBarDefaults.topAppBarColors()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(
                            Res.string.photos_title_template,
                            stringResource(state.photosListDisplayOrder.titleRes)
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onEvent(PhotosEvent.ToggleListOrderMenu(!state.isOrderMenuExpanded)) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.Sort,
                            contentDescription = stringResource(
                                Res.string.list_order_menu
                            )
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { onEvent(PhotosEvent.SelectSearch) }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(
                                Res.string.search
                            )
                        )
                    }
                },
                colors = colors,
                scrollBehavior = scrollBehavior
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing,
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        val lazyPhotoItems by rememberUpdatedState(newValue = state.photos.collectAsLazyPagingItems())

        val colorTransitionFraction by remember(scrollBehavior) {
            derivedStateOf {
                val overlappingFraction = scrollBehavior.state.overlappedFraction
                if (overlappingFraction > 0.01f) 1f else 0f
            }
        }

        val targetColor by animateColorAsState(
            targetValue = lerp(
                colors.containerColor,
                colors.scrolledContainerColor,
                FastOutLinearInEasing.transform(colorTransitionFraction)
            ),
            animationSpec = spring(stiffness = Spring.StiffnessMediumLow)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .animateContentSize(),
        ) {
            AnimatedVisibility(
                label = stringResource(Res.string.list_order_menu),
                visible = state.isOrderMenuExpanded,
                modifier = Modifier
                    .widthIn(max = 500.dp)
                    .background(targetColor)
                    .align(Alignment.CenterHorizontally)
            ) {
                if (state.isOrderMenuExpanded) {
                    OptionsMenu(
                        optionsStringRes = PhotoListDisplayOrder.entries.map { it.titleRes },
                        selectedOption = state.photosListDisplayOrder.ordinal,
                        onOptionSelected = {
                            onEvent(PhotosEvent.ChangeListOrder(it))
                            onEvent(PhotosEvent.ToggleListOrderMenu(false))
                        }
                    )
                }
            }

            PhotosGridContent(
                photoItems = lazyPhotoItems,
                onPhotoClicked = { onEvent(PhotosEvent.SelectPhoto(it)) },
            )
        }
    }
}

@Preview
@Composable
private fun PhotosScreenPreview() {
    WalleriaTheme {
        PhotosScreen(
            state = PhotosUiState(),
            onEvent = {}
        )
    }
}
