package com.vvchn.vktestcase.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Pokemons

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PokemonDetailed

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PokemonLocations
