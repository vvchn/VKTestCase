package com.vvchn.vktestcase.domain.usecases

import com.vvchn.vktestcase.domain.repository.PokeRepository
import kotlinx.coroutines.CoroutineScope

class RequestPokemonsUseCase(private val repository: PokeRepository) {
    operator fun invoke(
        coroutineScope: CoroutineScope,
        limit: Int? = null
    ) = repository.requestPokemons(coroutineScope, limit)
}