package com.vvchn.vktestcase.presentation.mainscreen.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.vvchn.vktestcase.presentation.mainscreen.PokemonsListScreenState
import com.vvchn.vktestcase.presentation.shared.NoItemsPlaceholder
import com.vvchn.vktestcase.presentation.shared.SnackbarErrorMessage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonsList(
    screenState: PokemonsListScreenState,
    snackbarHostState: SnackbarHostState,
    paddingValues: PaddingValues,
    navController: NavController,
) {
    val pullToRefreshState = rememberPullToRefreshState()
    val context = LocalContext.current

    var isSwipeToRefreshWorking by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .nestedScroll(pullToRefreshState.nestedScrollConnection)
    ) {
        screenState.pokemonPagingDataFlow.collectAsLazyPagingItems().apply {
            when {
                (itemCount == 0) && (loadState.refresh is LoadState.Loading) && (isSwipeToRefreshWorking == false) -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                (itemCount == 0) && (isErrorOccurredWhileRefreshing() || isErrorOccurredWhileAppending() || isEndOfPaginationReached()) -> {
                    NoItemsPlaceholder(
                        pullToRefreshState = pullToRefreshState
                    )
                }

                else -> {
                    PokemonsLazyColumn(
                        items = this@apply,
                        itemSpacing = 16.dp,
                        modifier = Modifier.fillMaxSize()
                    ) { pokemon ->
                        PokemonItem(pokemon = pokemon, navController = navController)
                    }
                }
            }

            PullToRefreshContainer(
                state = pullToRefreshState,
                modifier = Modifier
                    .align(Alignment.TopCenter),
            )

            if (pullToRefreshState.isRefreshing) {
                LaunchedEffect(true) {
                    isSwipeToRefreshWorking = true
                    this@apply.refresh()
                }
            }

            LaunchedEffect(isSwipeToRefreshWorking) {
                if (isSwipeToRefreshWorking) {
                    pullToRefreshState.startRefresh()
                }
            }

            LaunchedEffect(this@apply.loadState.refresh) {
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
                        error = (this@apply.loadState.append as LoadState.Error).error
                    )
                }
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
}