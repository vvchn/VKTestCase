package com.vvchn.vktestcase.data.remote.api.dtos

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("results") val results: List<NamedAPIResource>,
)
