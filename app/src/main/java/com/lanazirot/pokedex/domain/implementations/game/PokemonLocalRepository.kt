package com.lanazirot.pokedex.domain.implementations.game

import com.lanazirot.pokedex.domain.interfaces.game.IPokemonLocalRepository
import com.lanazirot.pokedex.domain.interfaces.game.IPokemonRepository
import com.lanazirot.pokedex.domain.models.game.Pokemon
import com.lanazirot.pokedex.domain.models.game.toPokemon
import javax.inject.Inject

class PokemonLocalRepository @Inject constructor (private var pokemonRepository: IPokemonRepository) :
    IPokemonLocalRepository {
    override suspend fun getPokemonList(): List<Pokemon> {
        return pokemonRepository.getPokemonList().map { it.toPokemon() }
    }
}