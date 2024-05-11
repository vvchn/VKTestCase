package com.vvchn.vktestcase.presentation.pokemonscreen

import com.vvchn.vktestcase.domain.models.PokemonDetailed

data class PokemonDetailedScreenState(
    val error: Throwable? = null,
    val isPokemonLoading: Boolean = true,
    val pokemon: PokemonDetailed? = null,
    val pokemonUrl: String = ""
)
