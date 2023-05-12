package com.lanazirot.pokedex.domain.interfaces.game

import com.lanazirot.pokedex.domain.models.game.Pokemon

interface IPokemonLocalRepository {
   suspend fun getPokemonList(): List<Pokemon>
}