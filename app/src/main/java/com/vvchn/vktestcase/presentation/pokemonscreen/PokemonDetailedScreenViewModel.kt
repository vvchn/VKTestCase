package com.vvchn.vktestcase.presentation.pokemonscreen

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvchn.avitotesttask.common.Resource
import com.vvchn.vktestcase.domain.usecases.RequestDetailedPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PokemonDetailedScreenViewModel @Inject constructor(
    private val requestDetailedPokemon: RequestDetailedPokemonUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(PokemonDetailedScreenState())
    val state: StateFlow<PokemonDetailedScreenState> = _state.asStateFlow()

    init {
        val url = Uri.decode(savedStateHandle.get<String>("pokemonEncodedUrl") ?: "")
        _state.update { it.copy(pokemonUrl = url) }
        requestPokemon(url)
    }

    fun requestPokemon(pokemonUrl: String = _state.value.pokemonUrl ) {
        requestDetailedPokemon(pokemonUrl).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.update { it.copy(pokemon = result.data, isPokemonLoading = false) }
                }
                is Resource.Loading -> {
                    _state.update { it.copy(error = null, isPokemonLoading = true) }
                }
                is Resource.Error -> {
                    _state.update { it.copy(error = result.throwable, isPokemonLoading = false) }
                }
            }
        }.launchIn(viewModelScope)
    }
}