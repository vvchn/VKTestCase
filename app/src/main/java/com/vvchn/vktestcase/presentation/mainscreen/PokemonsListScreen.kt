package com.vvchn.vktestcase.presentation.mainscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vvchn.vktestcase.R
import com.vvchn.vktestcase.presentation.mainscreen.elements.PokemonsList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonsListScreen(vm: PokemonsListScreenViewModel) {
    val uiState: PokemonsListScreenState by vm.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val pullToRefreshState = rememberPullToRefreshState()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.pokemons),
                            textAlign = TextAlign.Center,
                        )
                    }
                },
            )
        }
    ) { scaffoldPaddingValue ->
        PokemonsList(
            screenState = uiState,
            snackbarHostState = snackbarHostState,
            pullToRefreshState,
            scaffoldPaddingValue
        )
    }
}
