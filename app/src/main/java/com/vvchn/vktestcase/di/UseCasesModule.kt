package com.vvchn.vktestcase.di

import com.vvchn.vktestcase.domain.repository.PokeRepository
import com.vvchn.vktestcase.domain.usecases.RequestDetailedPokemonUseCase
import com.vvchn.vktestcase.domain.usecases.RequestPokemonsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Provides
    @Singleton
    fun provideRequestPokemonsUseCase(repository: PokeRepository) = RequestPokemonsUseCase(repository)

    @Provides
    @Singleton
    fun provideRequestDetailedPokemonUseCase(repository: PokeRepository) = RequestDetailedPokemonUseCase(repository)
}