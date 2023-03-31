package com.lanazirot.pokedex.domain.interfaces

import com.lanazirot.pokedex.domain.models.Pokemon
import com.lanazirot.pokedex.domain.models.PokemonGuessable
import com.lanazirot.pokedex.domain.models.User

interface IUserManager {
    fun getRandomUnseenPokemon(): PokemonGuessable
    fun setCurrentUser(user: User)
    fun getCurrentUser(): User?
    fun addSeenPokemon(pokemon: Pokemon)
    fun addToScoreLog(score: Int)
}