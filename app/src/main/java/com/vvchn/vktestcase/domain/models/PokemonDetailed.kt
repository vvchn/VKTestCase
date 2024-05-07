package com.vvchn.vktestcase.domain.models

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
