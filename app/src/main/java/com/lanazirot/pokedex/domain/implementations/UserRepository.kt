package com.lanazirot.pokedex.domain.implementations

import com.lanazirot.pokedex.domain.interfaces.IUserRepository
import com.lanazirot.pokedex.domain.models.Pokemon

class UserRepository : IUserRepository {
    override suspend fun getPokemonNotSeen(): List<Pokemon> {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonSeen(): List<Pokemon> {
        TODO("Not yet implemented")
    }
}