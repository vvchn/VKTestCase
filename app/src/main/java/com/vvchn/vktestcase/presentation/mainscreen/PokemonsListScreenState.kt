package com.vvchn.vktestcase.presentation.mainscreen

import androidx.paging.PagingData
import com.vvchn.vktestcase.domain.models.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class PokemonsListScreenState(
    val pokemonPagingDataFlow: Flow<PagingData<Pokemon>> = emptyFlow()
)
