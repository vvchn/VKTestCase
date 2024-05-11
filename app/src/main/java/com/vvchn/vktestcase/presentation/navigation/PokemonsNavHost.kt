package com.vvchn.vktestcase.presentation.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vvchn.vktestcase.presentation.mainscreen.PokemonsListScreen
import com.vvchn.vktestcase.presentation.pokemonscreen.PokemonDetailedScreen
import com.vvchn.vktestcase.presentation.pokemonscreen.PokemonDetailedScreenViewModel

@Composable
fun PokemonsNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.MainScreen.route,
        enterTransition = { slideInHorizontally { it * 1 } },
        exitTransition = { slideOutHorizontally { it * -1 } },
        popEnterTransition = { slideInHorizontally { it * -1 } },
        popExitTransition = { slideOutHorizontally { it * 1 } }

    ) {
        composable(Route.MainScreen.route) {
            PokemonsListScreen(
                navController = navController,
            )
        }
        composable(
            route = "${Route.PokemonDetailedScreen.route}/{pokemonEncodedUrl}",
            arguments = listOf(navArgument("pokemonEncodedUrl") { type = NavType.StringType })
        ) { backStackEntry ->
            val pokemonEncodedUrl = backStackEntry.arguments?.getString("pokemonEncodedUrl")
            if (pokemonEncodedUrl != null) {
                val pokemonDetailedScreenViewModel: PokemonDetailedScreenViewModel = hiltViewModel()
                PokemonDetailedScreen(
                    navController = navController,
                    vm = pokemonDetailedScreenViewModel,
                )
            }
        }
    }
}