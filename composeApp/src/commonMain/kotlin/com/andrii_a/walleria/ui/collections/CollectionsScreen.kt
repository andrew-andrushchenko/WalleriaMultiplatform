package com.andrii_a.walleria.ui.collections

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.paging.compose.collectAsLazyPagingItems
import com.andrii_a.walleria.ui.common.components.CollectionsGridContent
import org.jetbrains.compose.resources.stringResource
import walleriamultiplatform.composeapp.generated.resources.Res
import walleriamultiplatform.composeapp.generated.resources.collections
import walleriamultiplatform.composeapp.generated.resources.search

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionsScreen(
    state: CollectionsUiState,
    onEvent: (CollectionsEvent) -> Unit
) {
    val lazyCollectionItems by rememberUpdatedState(newValue = state.collections.collectAsLazyPagingItems())

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(Res.string.collections),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                actions = {
                    IconButton(onClick = { onEvent(CollectionsEvent.SelectSearch) }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(
                                Res.string.search
                            )
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing,
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        CollectionsGridContent(
            collectionItems = lazyCollectionItems,
            contentPadding = innerPadding,
            onCollectionClick = { onEvent(CollectionsEvent.SelectCollection(it)) }
        )
    }
}