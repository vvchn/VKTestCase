package com.vvchn.vktestcase.data.remote.api

import com.vvchn.vktestcase.data.remote.api.dtos.PokemonDetailedDto
import retrofit2.http.GET
import retrofit2.http.Url

interface PokemonDetailedApi {
    @GET
    suspend fun requestPokemonDetailed(
        @Url url: String
    ): PokemonDetailedDto
}