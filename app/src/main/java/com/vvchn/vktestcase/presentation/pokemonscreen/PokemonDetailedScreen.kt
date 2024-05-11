package com.vvchn.vktestcase.presentation.pokemonscreen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import com.vvchn.vktestcase.R
import com.vvchn.vktestcase.presentation.pokemonscreen.elements.PokemonDetailedItem
import com.vvchn.vktestcase.presentation.shared.NoItemsPlaceholder
import com.vvchn.vktestcase.presentation.shared.SnackbarErrorMessage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailedScreen(
    navController: NavController,
    vm: PokemonDetailedScreenViewModel,
) {
    val uiState: PokemonDetailedScreenState by vm.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val pullToRefreshState = rememberPullToRefreshState()
    val context = LocalContext.current
    var isSwipeToRefreshWorking by remember {
        mutableStateOf(false)
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.pokemon),
                        fontWeight = FontWeight.Bold,
                    )
                },
                actions = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "backToMainScreen",
                        )
                    }
                }
            )
        }
    ) { scaffoldPaddingValues ->
        Box(
            modifier = androidx.compose.ui.Modifier
                .padding(scaffoldPaddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .nestedScroll(pullToRefreshState.nestedScrollConnection)
        ) {
            uiState.apply {
                if (this.pokemon == null) {
                    if (this.isPokemonLoading && !isSwipeToRefreshWorking) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = MaterialTheme.colorScheme.primary
                        )
                    } else {
                        NoItemsPlaceholder(pullToRefreshState)
                    }
                } else {
                    PokemonDetailedItem(
                        pokemon = this.pokemon,
                        pullToRefreshState = pullToRefreshState,
                    )
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
                    vm.requestPokemon()
                }
            }

            LaunchedEffect(isSwipeToRefreshWorking) {
                if (isSwipeToRefreshWorking) {
                    pullToRefreshState.startRefresh()
                }
            }

            LaunchedEffect(uiState.isPokemonLoading) {
                if (isSwipeToRefreshWorking && !uiState.isPokemonLoading) {
                    isSwipeToRefreshWorking = false
                    pullToRefreshState.endRefresh()
                }
            }

            LaunchedEffect(uiState.error) {
                if (uiState.error != null) {
                    val error = uiState.error
                    SnackbarErrorMessage(
                        context = context,
                        snackbarHostState = snackbarHostState,
                        error = error!!
                    )
                }
            }

        }
    }
}