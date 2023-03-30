package com.lanazirot.pokedex.domain.implementations

import android.content.Context
import com.lanazirot.pokedex.domain.interfaces.IPokemonRepository
import com.lanazirot.pokedex.domain.models.Pokemon
import com.lanazirot.pokedex.domain.models.toPokemon

class PokemonLocalRepository(private val IPokemonRepository: IPokemonRepository) {
    suspend fun getPokemonList(): List<Pokemon>{
        return IPokemonRepository.getPokemonList().map { it.toPokemon() }
    }
}