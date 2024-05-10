package com.vvchn.vktestcase.presentation.mainscreen.elements

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.vvchn.vktestcase.presentation.mainscreen.PokemonsListScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonsList(
    screenState: PokemonsListScreenState,
    snackbarHostState: SnackbarHostState,
    pullToRefreshState: PullToRefreshState,
    paddingValues: PaddingValues
) {
    val context = LocalContext.current

    var isSwipeToRefreshWorking by remember {
        mutableStateOf(false)
    }

    screenState.pokemonsPagingHandle?.pokemonPagingDataFlow?.collectAsLazyPagingItems()?.apply {
        when {
            (loadState.refresh is LoadState.Loading) && (isSwipeToRefreshWorking == false) -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
                {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            (itemCount == 0) && (isErrorOccurredWhileRefreshing() || isErrorOccurredWhileAppending() || isEndOfPaginationReached()) -> {
                NoItemsPlaceholder(
                    paddingValues = paddingValues,
                    pullToRefreshState = pullToRefreshState
                )
            }

            else -> {
                PokemonsLazyColumn(
                    items = this@apply,
                    itemKey = { it.id.toString() },
                    itemSpacing = 8.dp
                ) { pokemon ->
                    PokemonItem(pokemon = pokemon)
                }
            }
        }

        if (pullToRefreshState.isRefreshing) {
            LaunchedEffect(true) {
                isSwipeToRefreshWorking = true
                this@apply.refresh()
            }
        }

        LaunchedEffect(key1 = isSwipeToRefreshWorking, key2 = this@apply.loadState.refresh) {
            if (isSwipeToRefreshWorking) {
                pullToRefreshState.startRefresh()
            }
            if (isSwipeToRefreshWorking && this@apply.loadState.refresh !is LoadState.Loading) {
                isSwipeToRefreshWorking = false
                pullToRefreshState.endRefresh()
            }
        }

        LaunchedEffect(this@apply.loadState) {
            if (this@apply.isErrorOccurredWhileAppending()) {
                SnackbarErrorMessage(
                    context = context,
                    snackbarHostState = snackbarHostState,
                    error = (this@apply.loadState.refresh as LoadState.Error).error
                )
            }
        }

        LaunchedEffect(this@apply.loadState) {
            if (this@apply.isErrorOccurredWhileRefreshing()) {
                SnackbarErrorMessage(
                    context = context,
                    snackbarHostState = snackbarHostState,
                    error = (this@apply.loadState.refresh as LoadState.Error).error
                )
            }
        }

    }
}