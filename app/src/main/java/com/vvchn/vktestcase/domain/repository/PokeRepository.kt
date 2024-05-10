package com.vvchn.vktestcase.domain.repository

import androidx.paging.PagingData
import com.vvchn.vktestcase.domain.models.Pokemon
import com.vvchn.vktestcase.domain.models.PokemonDetailed
import kotlinx.coroutines.flow.Flow

interface PokeRepository {
    fun requestPokemons(
        limit: Int?
    ): Flow<PagingData<Pokemon>>

    suspend fun requestDetailedPokemon(
        url: String
    ): PokemonDetailed
}
