package com.vvchn.vktestcase.data.remote.repository

import com.vvchn.vktestcase.data.remote.api.PokeApi
import com.vvchn.vktestcase.data.remote.paging.PokemonsPagingHandle
import com.vvchn.vktestcase.data.remote.utils.toPokemonDetailed
import com.vvchn.vktestcase.domain.models.PokemonDetailed
import com.vvchn.vktestcase.domain.repository.PokeRepository
import kotlinx.coroutines.CoroutineScope

class PokeRepositroyImpl(private val api: PokeApi) : PokeRepository {
    override fun requestPokemons(
        coroutineScope: CoroutineScope,
        limit: Int?,
    ): PokemonsPagingHandle =
        PokemonsPagingHandle(
            api = api,
            coroutineScope = coroutineScope,
            limit = limit
        )

    override suspend fun requestDetailedPokemon(url: String): PokemonDetailed {
        val apiPokemonResponse = api.requestPokemonDetailed(url)
        val apiPokemonLocationsResponse = api.requestPokemonLocations(apiPokemonResponse.locationsUrl)

        return apiPokemonResponse.toPokemonDetailed(apiPokemonLocationsResponse)
    }

}