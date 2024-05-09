package com.vvchn.vktestcase.domain.usecases

import com.vvchn.vktestcase.domain.repository.PokeRepository
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class RequestPokemonsUseCase @Inject constructor (private val repository: PokeRepository) {
    operator fun invoke(
        coroutineScope: CoroutineScope,
        limit: Int? = null
    ) = repository.requestPokemons(coroutineScope, limit)
}