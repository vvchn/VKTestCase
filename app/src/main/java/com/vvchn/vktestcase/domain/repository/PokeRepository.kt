package com.vvchn.vktestcase.domain.repository

import com.vvchn.vktestcase.data.remote.paging.PokemonsPagingHandle
import com.vvchn.vktestcase.domain.models.PokemonDetailed
import kotlinx.coroutines.CoroutineScope

interface PokeRepository {
    fun requestPokemons(
        coroutineScope: CoroutineScope,
        limit: Int?
    ): PokemonsPagingHandle

    suspend fun requestDetailedPokemon(
        url: String
    ): PokemonDetailed
}
