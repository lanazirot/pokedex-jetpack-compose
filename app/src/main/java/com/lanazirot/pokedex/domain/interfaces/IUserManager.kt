package com.lanazirot.pokedex.domain.interfaces

import com.lanazirot.pokedex.domain.models.Pokemon
import com.lanazirot.pokedex.domain.models.User

interface IUserManager {
    fun setCurrentUser(user: User)
    fun getCurrentUser(): User?
    fun addSeenPokemon(pokemon: Pokemon)
    fun addToScoreLog(score: Int)
}