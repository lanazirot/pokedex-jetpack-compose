package com.lanazirot.pokedex.domain.implementations

import com.lanazirot.pokedex.domain.interfaces.IPokemonLocalRepository
import com.lanazirot.pokedex.domain.interfaces.IPokemonRepository
import com.lanazirot.pokedex.domain.models.Pokemon
import com.lanazirot.pokedex.domain.models.toPokemon
import javax.inject.Inject

class PokemonLocalRepository @Inject constructor (private var pokemonRepository: IPokemonRepository) : IPokemonLocalRepository {
    override suspend fun getPokemonList(): List<Pokemon> {
        return pokemonRepository.getPokemonList().map { it.toPokemon() }
    }
}