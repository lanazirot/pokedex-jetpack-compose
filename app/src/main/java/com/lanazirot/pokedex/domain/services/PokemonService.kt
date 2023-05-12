package com.lanazirot.pokedex.domain.services

import com.lanazirot.pokedex.domain.interfaces.game.IPokemonRepository
import com.lanazirot.pokedex.domain.interfaces.game.IPokemonService
import com.lanazirot.pokedex.domain.models.game.PokemonMapped
import javax.inject.Inject

class PokemonService @Inject constructor(
    private val pokemonRepository: IPokemonRepository
) : IPokemonService {
    override suspend fun getPokemons(): List<PokemonMapped> {
        return pokemonRepository.getPokemonList()
    }

    override suspend fun getPokemon(id: Int): PokemonMapped {
        return pokemonRepository.getPokemonById(id)
    }
}