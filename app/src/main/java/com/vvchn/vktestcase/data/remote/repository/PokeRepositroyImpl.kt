package com.vvchn.vktestcase.data.remote.repository

import com.vvchn.vktestcase.data.remote.api.PokeApi
import com.vvchn.vktestcase.data.remote.api.PokemonLocationsApi
import com.vvchn.vktestcase.data.remote.api.PokemonsApi
import com.vvchn.vktestcase.data.remote.api.PokemonDetailedApi
import com.vvchn.vktestcase.data.remote.paging.PokemonsPagingHandle
import com.vvchn.vktestcase.data.remote.utils.toPokemonDetailed
import com.vvchn.vktestcase.domain.models.PokemonDetailed
import com.vvchn.vktestcase.domain.repository.PokeRepository
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class PokeRepositroyImpl @Inject constructor (
    private val pokeApi: PokeApi,
    private val pokemonsApi: PokemonsApi,
    private val pokemonDetailedApi: PokemonDetailedApi,
    private val pokemonsLocationsApi: PokemonLocationsApi,
) : PokeRepository {
    override fun requestPokemons(
        coroutineScope: CoroutineScope,
        limit: Int?,
    ): PokemonsPagingHandle =
        PokemonsPagingHandle(
            pokeApi = pokeApi,
            pokemonsApi = pokemonsApi,
            coroutineScope = coroutineScope,
            limit = limit,
        )

    override suspend fun requestDetailedPokemon(url: String): PokemonDetailed {
        val apiPokemonResponse = pokemonDetailedApi.requestPokemonDetailed(url)
        val apiPokemonLocationsResponse = pokemonsLocationsApi.requestPokemonLocations(apiPokemonResponse.locationsUrl)

        return apiPokemonResponse.toPokemonDetailed(apiPokemonLocationsResponse)
    }

}