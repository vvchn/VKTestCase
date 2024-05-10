package com.vvchn.vktestcase.data.remote.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.vvchn.vktestcase.data.remote.api.dtos.PokemonDetailedDto
import com.vvchn.vktestcase.data.remote.api.dtos.PokemonDto
import com.vvchn.vktestcase.data.remote.api.dtos.StatDto
import com.vvchn.vktestcase.data.remote.utils.Constants.BASE_URL
import com.vvchn.vktestcase.data.remote.utils.Constants.POKEMON
import java.lang.reflect.Type

class PokemonDetailedDeserializer : JsonDeserializer<PokemonDetailedDto> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): PokemonDetailedDto {
        val abilitiesList: MutableList<String> = mutableListOf()
        val formsList: MutableList<String> = mutableListOf()
        val imageUrl: String
        val height: Int
        val id: Int
        val locationsUrl: String
        val name: String
        val stats: MutableList<StatDto> = mutableListOf()
        val weight: Int

        val jsonObject = json?.asJsonObject
        val abilitiesArray = jsonObject?.getAsJsonArray("abilities")
        val formsArray = jsonObject?.getAsJsonArray("forms")
        val statsArray = jsonObject?.getAsJsonArray("stats")

        // Abilities
        abilitiesArray?.forEach { abilityElement ->
            val abilityObject = abilityElement.asJsonObject
            val abilityName = abilityObject.getAsJsonObject("ability").get("name").asString
            abilitiesList.add(abilityName)
        }

        // Forms
        formsArray?.forEach { formElement ->
            val formObject = formElement.asJsonObject
            val formName = formObject.get("name").asString
            formsList.add(formName)
        }

        // Link to Pokemon image
        imageUrl = jsonObject?.let {
            it.getAsJsonObject("sprites").getAsJsonObject("other").getAsJsonObject("dream_world")
                .get("front_default").asString
        } ?: ""

        // Stats
        statsArray?.forEach { statElement ->
            val statObject = statElement.asJsonObject
            val statName = statObject.getAsJsonObject("stat").get("name").asString
            val baseStat = statObject.get("base_stat").asInt
            stats.add(StatDto(baseStat, statName))
        }

        // Other parameters
        height = jsonObject?.let { it.get("height").asInt } ?: 0
        id = jsonObject?.let { it.get("id").asInt } ?: 0
        locationsUrl = jsonObject?.let { it.get("location_area_encounters").asString } ?: ""
        name = jsonObject?.let { it.get("name").asString } ?: ""
        weight = jsonObject?.let { it.get("weight").asInt } ?: 0

        return PokemonDetailedDto(
            abilities = abilitiesList,
            forms = formsList,
            image = imageUrl,
            height = height,
            id = id,
            locationsUrl = locationsUrl,
            name = name,
            stats = stats,
            weight = weight
        )
    }
}

class PokemonDeserializer : JsonDeserializer<PokemonDto> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): PokemonDto {
        val id: Int
        val imageUrl: String
        val name: String

        val jsonObject = json?.asJsonObject

        id = jsonObject?.let { it.get("id").asInt } ?: 0
        name = jsonObject?.let { it.get("name").asString } ?: ""

        // Link to Pokemon image
        imageUrl = jsonObject?.let {
            it.getAsJsonObject("sprites").getAsJsonObject("other")?.getAsJsonObject("dream_world")
                ?.get("front_default")?.asString
        } ?: ""

        return PokemonDto(
            id = id,
            name = name,
            image = imageUrl,
            url = BASE_URL + POKEMON + "/$id"
        )
    }
}

class PokemonLocationsDeserializer : JsonDeserializer<List<String>> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<String> {
        val locations: MutableList<String> = mutableListOf()

        val jsonObject = json?.asJsonArray
        jsonObject?.forEach {
            val locationObject = it.asJsonObject
            val name = locationObject.getAsJsonObject("location_area").get("name").asString ?: ""
            locations.add(name)
        }

        return locations
    }

}