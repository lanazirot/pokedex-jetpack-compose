package com.lanazirot.pokedex.domain.interfaces.game

import com.lanazirot.pokedex.domain.models.game.Pokemon
import com.lanazirot.pokedex.domain.models.game.Score
import com.lanazirot.pokedex.domain.models.game.PokemonGuessable
import com.lanazirot.pokedex.domain.models.user.User

interface IUserManager {
    suspend fun getRandomUnseenPokemon(): PokemonGuessable
    fun setCurrentUser(user: User)
    fun getCurrentUser(): User?
    fun addSeenPokemon(pokemon: Pokemon)
    fun addToScoreLog(score: Int)
    fun getTopThreeScores() : List<Score>
    fun getPokemonFound() : List<Pokemon>
    fun pokemonFoundByTypeCount(type: String): Int
    suspend fun getPokemonNotFound() : List<Pokemon>
    fun pokemonLegendaryFoundCount() :Int
    fun attemptsCount() :Int
    fun getPokedexProgress() :Int
    suspend fun getAllPokemonList(): List<Pokemon>
    fun isPokedexCompleted() : Boolean
}