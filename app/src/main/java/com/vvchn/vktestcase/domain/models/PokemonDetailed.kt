package com.vvchn.vktestcase.domain.models

import androidx.compose.runtime.Immutable

@Immutable
data class PokemonDetailed(
    val abilities: List<String>,
    val forms: List<String>,
    val image: String?,
    val height: Int,
    val id: Int,
    val locations: List<String>,
    val name: String,
    val stats: List<Stat>,
    val weight: Int,
)
