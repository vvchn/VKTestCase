package com.vvchn.vktestcase.di

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.vvchn.vktestcase.data.remote.api.dtos.PokemonDetailedDto
import com.vvchn.vktestcase.data.remote.api.dtos.PokemonDto
import com.vvchn.vktestcase.data.remote.utils.PokemonDeserializer
import com.vvchn.vktestcase.data.remote.utils.PokemonDetailedDeserializer
import com.vvchn.vktestcase.data.remote.utils.PokemonLocationsDeserializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConvertersModule {

    @Provides
    @Singleton
    @Pokemons
    fun providePokemonsGson() = GsonBuilder()
        .registerTypeAdapter(PokemonDto::class.java, PokemonDeserializer())
        .create()


    @Provides
    @Singleton
    @PokemonDetailed
    fun providePokemonDetailedGson() = GsonBuilder()
        .registerTypeAdapter(PokemonDetailedDto::class.java, PokemonDetailedDeserializer())
        .create()

    @Provides
    @Singleton
    @PokemonLocations
    fun providePokemonLocationsGson() = GsonBuilder()
        .registerTypeAdapter(
            TypeToken.getParameterized(List::class.java, String::class.java).type,
            PokemonLocationsDeserializer()
        )
        .create()
}