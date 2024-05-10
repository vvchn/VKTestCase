package com.vvchn.vktestcase.presentation.mainscreen.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.vvchn.vktestcase.domain.models.Pokemon

@Composable
fun PokemonsLazyColumn(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
    items: LazyPagingItems<Pokemon>,
    itemSpacing: Dp,
    contentPadding: PaddingValues = PaddingValues(),
    itemKey: (Pokemon) -> String,
    content: @Composable (item: Pokemon) -> Unit,
) {
    LazyColumn(
        state = lazyListState,
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(itemSpacing),
    ) {
        items(
            count = items.itemCount,
            key = { index ->
                val item = items[index]
                if (item != null) itemKey(item) else "empty"
            }
        ) { index ->
            items[index]?.let { item ->
                content(item)
            }
        }
        if (items.loadState.refresh is LoadState.Loading) {
            item(
                key = "LoadingProgress"
            ) {
                LoadingProgressIndicator()
            }
        }
    }
}

@Composable
fun LoadingProgressIndicator() {
    Box(
        Modifier
            .fillMaxSize()
            .wrapContentHeight()
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(32.dp)
        )
    }
}