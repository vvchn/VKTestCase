package com.vvchn.vktestcase.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vvchn.vktestcase.data.remote.api.PokeApi
import com.vvchn.vktestcase.data.remote.api.PokemonDetailedApi
import com.vvchn.vktestcase.data.remote.api.PokemonLocationsApi
import com.vvchn.vktestcase.data.remote.api.PokemonsApi
import com.vvchn.vktestcase.data.remote.paging.PokemonsPagingSource
import com.vvchn.vktestcase.data.remote.utils.Constants.DEFAULT_PAGE_SIZE
import com.vvchn.vktestcase.data.remote.utils.toPokemonDetailed
import com.vvchn.vktestcase.di.PokemonLocations
import com.vvchn.vktestcase.di.Pokemons
import com.vvchn.vktestcase.domain.models.Pokemon
import com.vvchn.vktestcase.domain.models.PokemonDetailed
import com.vvchn.vktestcase.domain.repository.PokeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokeRepositroyImpl @Inject constructor (
    private val pokeApi: PokeApi,
    @Pokemons private val pokemonsApi: PokemonsApi,
    @com.vvchn.vktestcase.di.PokemonDetailed private val pokemonDetailedApi: PokemonDetailedApi,
    @PokemonLocations private val pokemonsLocationsApi: PokemonLocationsApi,
) : PokeRepository {
    override fun requestPokemons(limit: Int?): Flow<PagingData<Pokemon>> {
        val _limit = (limit ?: DEFAULT_PAGE_SIZE)
        return Pager(
            PagingConfig(
                prefetchDistance = 2,
                pageSize = _limit,
                initialLoadSize = _limit,
            )
        ) {
            PokemonsPagingSource(
                pokeApi = pokeApi,
                pokemonsApi = pokemonsApi
            )
        }.flow
    }

    override suspend fun requestDetailedPokemon(url: String): PokemonDetailed {
        val apiPokemonResponse = pokemonDetailedApi.requestPokemonDetailed(url)
        val apiPokemonLocationsResponse = pokemonsLocationsApi.requestPokemonLocations(apiPokemonResponse.locationsUrl)

        return apiPokemonResponse.toPokemonDetailed(apiPokemonLocationsResponse)
    }

}