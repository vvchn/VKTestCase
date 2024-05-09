package com.vvchn.vktestcase.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Url

interface PokemonLocationsApi {
    @GET
    suspend fun  requestPokemonLocations(
        @Url url: String
    ): List<String>
}