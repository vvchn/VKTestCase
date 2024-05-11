package com.vvchn.vktestcase.presentation.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vvchn.vktestcase.presentation.mainscreen.PokemonsListScreen
import com.vvchn.vktestcase.presentation.mainscreen.PokemonsListScreenViewModel
import com.vvchn.vktestcase.presentation.pokemonscreen.PokemonDetailedScreen
import com.vvchn.vktestcase.presentation.pokemonscreen.PokemonDetailedScreenViewModel

@Composable
fun PokemonsNavHost(
    navController: NavHostController,
    pokemonsScreenViewModel: PokemonsListScreenViewModel,
    pokemonDetailedScreenViewModel: PokemonDetailedScreenViewModel
) {
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
                vm = pokemonsScreenViewModel
            )
        }
        composable(
            route = "${Route.PokemonDetailedScreen.route}/{pokemonUrl}",
            arguments = listOf(navArgument("pokemonUrl") { type = NavType.StringType })
        ) { backStackEntry ->
            val pokemonUrl = backStackEntry.arguments?.getString("pokemonUrl")
            if (pokemonUrl != null) {
                PokemonDetailedScreen(
                    navController = navController,
                    vm = pokemonDetailedScreenViewModel,
                    pokemonUrl = pokemonUrl
                )
            }
        }
    }
}