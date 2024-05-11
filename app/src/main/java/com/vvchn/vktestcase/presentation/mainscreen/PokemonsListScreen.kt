package com.vvchn.vktestcase.presentation.mainscreen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.vvchn.vktestcase.R
import com.vvchn.vktestcase.presentation.mainscreen.elements.PokemonsList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonsListScreen(
    navController: NavController,
    vm: PokemonsListScreenViewModel = hiltViewModel()
) {
    val uiState: PokemonsListScreenState by vm.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.pokemons),
                        fontWeight = FontWeight.Bold,
                    )
                },
            )
        }
    ) { scaffoldPaddingValues ->
        PokemonsList(
            screenState = uiState,
            snackbarHostState = snackbarHostState,
            paddingValues = scaffoldPaddingValues,
            navController = navController,
        )
    }
}

