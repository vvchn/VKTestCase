package com.vvchn.vktestcase.data.remote.api

import com.vvchn.vktestcase.data.remote.api.dtos.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PokeApi {

    @GET("pokemon")
    suspend fun requestResourcesList(
        @Query("limit") limit: Int?
    ): ApiResponse

    @GET
    suspend fun continueRequestResoursesList(
        @Url url: String
    ): ApiResponse
}