package com.vvchn.vktestcase.data.remote.api.dtos

data class PokemonDetailedDto(
    val abilities: List<String>,
    val forms: List<String>,
    val image: String?,
    val height: Int,
    val id: Int,
    val locationsUrl: String,
    val name: String,
    val stats: List<StatDto>,
    val weight: Int,
)
