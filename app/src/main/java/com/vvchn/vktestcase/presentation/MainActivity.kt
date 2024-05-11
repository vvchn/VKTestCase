package com.vvchn.vktestcase.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.vvchn.vktestcase.presentation.mainscreen.PokemonsListScreenViewModel
import com.vvchn.vktestcase.presentation.navigation.PokemonsNavHost
import com.vvchn.vktestcase.presentation.pokemonscreen.PokemonDetailedScreenViewModel
import com.vvchn.vktestcase.presentation.ui.theme.VKTestCaseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val pokemonsScreenViewModel: PokemonsListScreenViewModel = hiltViewModel()
            val pokemonDetailedScreenViewModel: PokemonDetailedScreenViewModel = hiltViewModel()
            val navController = rememberNavController()
            VKTestCaseTheme {
                PokemonsNavHost(
                    navController = navController,
                    pokemonsScreenViewModel = pokemonsScreenViewModel,
                    pokemonDetailedScreenViewModel = pokemonDetailedScreenViewModel,
                )
            }
        }
    }
}