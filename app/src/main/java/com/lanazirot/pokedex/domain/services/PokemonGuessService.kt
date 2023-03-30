package com.lanazirot.pokedex.domain.services

import com.lanazirot.pokedex.domain.interfaces.IPokemonGuessService
import com.lanazirot.pokedex.domain.interfaces.IPokemonRepository
import javax.inject.Inject

class PokemonGuessService
@Inject constructor(
    private val pokemonRepository: IPokemonRepository
) : IPokemonGuessService {

    override suspend fun guessPokemon(id: Int): Boolean {
        val pokemon = pokemonRepository.getPokemonById(id)
        return pokemon.id == id
    }

}