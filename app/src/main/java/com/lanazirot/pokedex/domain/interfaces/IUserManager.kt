package com.lanazirot.pokedex.domain.interfaces

import com.lanazirot.pokedex.domain.models.Pokemon
import com.lanazirot.pokedex.domain.models.Score
import com.lanazirot.pokedex.domain.models.PokemonGuessable
import com.lanazirot.pokedex.domain.models.User

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