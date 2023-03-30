package com.lanazirot.pokedex.domain.interfaces

import com.lanazirot.pokedex.domain.models.Pokemon

interface IUserRepository {
    suspend fun getPokemonNotSeen(): List<Pokemon>
    suspend fun getPokemonSeen(): List<Pokemon>
}