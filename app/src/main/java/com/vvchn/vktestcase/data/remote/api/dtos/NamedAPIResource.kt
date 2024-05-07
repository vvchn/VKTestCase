package com.vvchn.vktestcase.data.remote.api.dtos

import com.google.gson.annotations.SerializedName

data class NamedAPIResource(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)
