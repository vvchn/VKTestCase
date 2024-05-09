package com.vvchn.vktestcase.data.remote.api

import com.vvchn.vktestcase.data.remote.api.dtos.PokemonDto
import retrofit2.http.GET
import retrofit2.http.Url

interface PokemonsApi {
    @GET
    suspend fun requestPokemon(
        @Url url: String
    ): PokemonDto
}