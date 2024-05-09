package com.vvchn.vktestcase.data.remote.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.vvchn.vktestcase.data.remote.api.PokeApi
import com.vvchn.vktestcase.data.remote.api.PokemonsApi
import com.vvchn.vktestcase.data.remote.utils.Constants.DEFAULT_PAGE_SIZE
import com.vvchn.vktestcase.domain.models.Pokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class PokemonsPagingHandle(
    pokeApi: PokeApi,
    pokemonsApi: PokemonsApi,
    coroutineScope: CoroutineScope,
    limit: Int?
) {
    private var pagingSource: PagingSource<Int, Pokemon>? = null

    @Suppress("MemberVisibilityCanBePrivate")
    val pokemonPagingDataFlow: Flow<PagingData<Pokemon>> by lazy {
        Pager(
            PagingConfig(
                limit ?: DEFAULT_PAGE_SIZE
            )
        ) {
            PokemonsPagingSource(
                pokeApi = pokeApi,
                pokemonsApi = pokemonsApi
            ).apply { pagingSource = this }
        }
            .flow
            .cachedIn(coroutineScope)
    }

    fun refresh() {
        pagingSource?.invalidate()
    }

}