package com.lanazirot.pokedex.domain.interfaces

import android.content.Context
import com.lanazirot.pokedex.domain.models.PokemonMapped

interface IPokemonRepository {
    suspend fun getPokemonList(): List<PokemonMapped>
    suspend fun getPokemonById(id: Int): PokemonMapped
}