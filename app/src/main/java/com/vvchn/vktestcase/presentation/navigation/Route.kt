package com.vvchn.vktestcase.presentation.navigation

sealed class Route(val route: String) {
    object MainScreen : Route(route = "main_screen")
    object PokemonDetailedScreen : Route(route = "pokemon_detailed_screen")
}