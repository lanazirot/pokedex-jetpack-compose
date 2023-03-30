package com.lanazirot.pokedex.domain.interfaces

import com.lanazirot.pokedex.domain.models.Pokemon

interface IPokemonLocalRepository {
   suspend fun getPokemonList(): List<Pokemon>
}