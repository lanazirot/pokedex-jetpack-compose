package com.lanazirot.pokedex.domain.interfaces.game

import com.lanazirot.pokedex.domain.models.game.PokemonMapped

interface IPokemonRepository {
    suspend fun getPokemonList(): List<PokemonMapped>
    suspend fun getPokemonById(id: Int): PokemonMapped
}