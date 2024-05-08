package com.vvchn.vktestcase.data.remote.utils

import com.vvchn.vktestcase.data.remote.api.dtos.PokemonDetailedDto
import com.vvchn.vktestcase.data.remote.api.dtos.PokemonDto
import com.vvchn.vktestcase.data.remote.api.dtos.StatDto
import com.vvchn.vktestcase.domain.models.Pokemon
import com.vvchn.vktestcase.domain.models.PokemonDetailed
import com.vvchn.vktestcase.domain.models.Stat

internal fun PokemonDto.toPokemon(): Pokemon {
    return Pokemon(
        id = id,
        image = image,
        name = name,
        url = url,
    )
}

internal fun StatDto.toStat(): Stat {
    return Stat(
        baseStat = baseStat,
        name = name
    )
}

internal fun PokemonDetailedDto.toPokemonDetailed(locations: List<String>): PokemonDetailed {
    return PokemonDetailed(
     abilities = abilities,
     forms = forms,
     image = image,
     height = height,
     id = id,
     locations = locations,
     name = name,
     stats = stats.map { statDto: StatDto -> statDto.toStat() },
     weight = weight
    )
}
