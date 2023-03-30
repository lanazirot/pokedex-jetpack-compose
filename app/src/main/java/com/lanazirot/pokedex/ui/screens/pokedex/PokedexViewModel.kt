package com.lanazirot.pokedex.ui.screens.pokedex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lanazirot.pokedex.domain.interfaces.IPokemonLocalRepository
import com.lanazirot.pokedex.domain.models.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(private val pokemonLocalRepository: IPokemonLocalRepository) : ViewModel() {

    var pokemonList = emptyList<Pokemon>()

    init {
       viewModelScope.launch {
           pokemonList = pokemonLocalRepository.getPokemonList()
       }
    }

}