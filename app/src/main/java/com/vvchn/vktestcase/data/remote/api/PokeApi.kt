package com.vvchn.vktestcase.data.remote.api

import com.vvchn.vktestcase.data.remote.api.dtos.ApiResponse
import com.vvchn.vktestcase.data.remote.api.dtos.PokemonDetailedDto
import com.vvchn.vktestcase.data.remote.api.dtos.PokemonDto
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PokeApi {

    @GET("pokemon")
    suspend fun requestPokemonsList(
        @Query("limit") limit: Int?
    ): ApiResponse

    @GET
    suspend fun continueRequestPokemonsList(
        @Url url: String
    ): ApiResponse

    @GET
    suspend fun requestPokemon(
        @Url url: String
    ): PokemonDto

    @GET
    suspend fun requestPokemonDetailed(
        @Url url: String
    ): PokemonDetailedDto

    @GET
    suspend fun  requestPokemonLocations(
        @Url url: String
    ): List<String>
}