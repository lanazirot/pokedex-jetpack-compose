package com.lanazirot.pokedex.domain.interfaces.game

import com.lanazirot.pokedex.domain.models.game.PokemonMapped

interface IPokemonService {
    suspend fun getPokemons(): List<PokemonMapped>
    suspend fun getPokemon(id: Int): PokemonMapped
}