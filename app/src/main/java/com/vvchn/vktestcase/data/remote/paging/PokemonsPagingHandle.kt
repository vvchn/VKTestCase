package com.vvchn.vktestcase.data.remote.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.vvchn.vktestcase.data.remote.api.PokeApi
import com.vvchn.vktestcase.data.remote.utils.Constants.DEFAULT_PAGE_SIZE
import com.vvchn.vktestcase.domain.models.Pokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class PokemonsPagingHandle(
    api: PokeApi,
    coroutineScope: CoroutineScope,
    limit: Int?
) {
    private var pagingSource: PagingSource<Int, Pokemon>? = null

    @Suppress("MemberVisibilityCanBePrivate")
    val pokemonPagingDataFlow: Flow<PagingData<Pokemon>>

    init {
        pokemonPagingDataFlow = Pager(
            PagingConfig(
                limit ?: DEFAULT_PAGE_SIZE
            )
        ) { PokemonsPagingSource(api = api).apply { pagingSource = this } }
            .flow
            .cachedIn(coroutineScope)

    }

    fun refresh() {
        pagingSource?.invalidate()
    }

}