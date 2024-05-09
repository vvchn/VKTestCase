package com.vvchn.vktestcase.di

import com.vvchn.vktestcase.data.remote.repository.PokeRepositroyImpl
import com.vvchn.vktestcase.domain.repository.PokeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindPokeRepository(
        pokeRepositoryImpl: PokeRepositroyImpl
    ): PokeRepository
}