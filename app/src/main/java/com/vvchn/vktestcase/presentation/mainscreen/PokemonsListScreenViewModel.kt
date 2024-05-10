package com.vvchn.vktestcase.presentation.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvchn.vktestcase.domain.usecases.RequestPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonsListScreenViewModel @Inject constructor (
    private val pokemonsSource: RequestPokemonsUseCase
): ViewModel() {

    private val _state = MutableStateFlow(PokemonsListScreenState())
    val state: StateFlow<PokemonsListScreenState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val pokemonsHandle = pokemonsSource(coroutineScope = viewModelScope)
            _state.update { it.copy(pokemonsPagingHandle = pokemonsHandle) }
        }
    }

    fun invalidate() {
        _state.value.pokemonsPagingHandle?.invalidate()
    }


    
}