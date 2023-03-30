package com.lanazirot.pokedex.domain.implementations

import android.content.Context
import com.lanazirot.pokedex.domain.models.toPokemon

class PokemonLocalRepository(private val pokemonRepository: PokemonRepository = PokemonRepository()) {
    suspend fun getPokemonList() = pokemonRepository.getPokemonList().map { it.toPokemon() }
}