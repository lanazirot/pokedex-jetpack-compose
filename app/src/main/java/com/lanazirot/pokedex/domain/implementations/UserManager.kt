package com.lanazirot.pokedex.domain.implementations

import com.lanazirot.pokedex.domain.interfaces.IUserManager
import com.lanazirot.pokedex.domain.models.Pokemon
import com.lanazirot.pokedex.domain.models.Score
import com.lanazirot.pokedex.domain.models.User
import java.util.*
import javax.inject.Inject

class UserManager @Inject constructor() : IUserManager {

    private var currentUser: User? = null

    init {
        currentUser = User()
    }

    override fun setCurrentUser(user: User) {
        currentUser = user
    }

    override fun getCurrentUser(): User? {
        return currentUser
    }

    override fun addSeenPokemon(pokemon: Pokemon) {
        currentUser?.foundPokemonList?.add(pokemon)
    }

    override fun addToScoreLog(score: Int) {
        val myScore = Score(score = score, date = "")
        currentUser?.scoreLog?.add(myScore)
    }

    override fun getTopThreeScores(): List<Score> {
        TODO("Not yet implemented")
    }

    override fun getPokemonFound(): List<Pokemon> {
        TODO("Not yet implemented")
    }

    override fun pokemonFoundByTypeCount(type: String): Int {
        TODO("Not yet implemented")
    }

    override fun pokemonLegendaryFoundCount(): Int {
        TODO("Not yet implemented")
    }

    override fun attemptsCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getPokedexProgress(): Int {
        TODO("Not yet implemented")
    }

    override fun getAllPokemonList(): List<Pokemon> {
        TODO("Not yet implemented")
    }

    override fun getPokemonNotFound(): List<Pokemon> {
        return getAllPokemonList().subtract(getPokemonFound()).toMutableList()
    }
}