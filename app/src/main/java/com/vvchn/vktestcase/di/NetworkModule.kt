package com.vvchn.vktestcase.di

import com.google.gson.Gson
import com.vvchn.vktestcase.data.remote.api.PokeApi
import com.vvchn.vktestcase.data.remote.api.PokemonsApi
import com.vvchn.vktestcase.data.remote.api.PokemonDetailedApi
import com.vvchn.vktestcase.data.remote.api.PokemonLocationsApi
import com.vvchn.vktestcase.data.remote.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttp(
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providePokeApi(client: OkHttpClient): PokeApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApi::class.java)
    }

    @Provides
    @Singleton
    @Pokemons
    fun providePokemonsApi(client: OkHttpClient, gson: Gson): PokemonsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(PokemonsApi::class.java)
    }

    @Provides
    @Singleton
    @PokemonDetailed
    fun providePokemonDetailedApi(client: OkHttpClient, gson: Gson): PokemonDetailedApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(PokemonDetailedApi::class.java)
    }

    @Provides
    @Singleton
    @PokemonLocations
    fun providePokemonLocationsApi(client: OkHttpClient, gson: Gson): PokemonLocationsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(PokemonLocationsApi::class.java)
    }
}

