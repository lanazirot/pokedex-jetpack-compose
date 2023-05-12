package com.lanazirot.pokedex.domain.implementations.game

import com.lanazirot.pokedex.domain.constants.GameConstants
import com.lanazirot.pokedex.domain.interfaces.game.IPokemonLocalRepository
import com.lanazirot.pokedex.domain.interfaces.game.IUserManager
import com.lanazirot.pokedex.domain.models.game.*
import com.lanazirot.pokedex.domain.models.user.User
import java.util.*
import javax.inject.Inject

class UserManager @Inject constructor(private val pokemonLocalRepository: IPokemonLocalRepository) :
    IUserManager {
    private val totalPokemon = GameConstants.TOTAL_POKEMON
    private var currentUser: User? = null


    override suspend fun getRandomUnseenPokemon(): PokemonGuessable {
        val pokemonNotFoundList = getPokemonNotFound()
        val allPokemonList = mutableListOf<Pokemon>()
        allPokemonList.addAll(pokemonLocalRepository.getPokemonList())

        val random = Random()
        val pokemonsAnswers = mutableListOf<Answer>()

        val pokemonAux = pokemonNotFoundList[random.nextInt(pokemonNotFoundList.size)]
        pokemonsAnswers.add(Answer(pokemon = pokemonAux, isCorrect = true))
        allPokemonList.remove(pokemonAux)

        for (i in 0..2) {
            val randomPokemon = allPokemonList[random.nextInt(allPokemonList.size)]
            pokemonsAnswers.add(Answer(pokemon = randomPokemon, isCorrect = false))
            allPokemonList.remove(randomPokemon)
        }

        pokemonsAnswers.shuffle()

        return PokemonGuessable(answers = pokemonsAnswers)
    }

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
        if(currentUser?.foundPokemonList?.size == totalPokemon) {
            currentUser?.pokedexCompleted = true
        }
    }

    override fun addToScoreLog(score: Int) {
        val myScore = Score(score = score, date = Date())
        currentUser?.scoreLog?.add(myScore)
    }

    override fun getTopThreeScores(): List<Score> {
        return currentUser?.scoreLog?.sortedByDescending { it.score }?.take(3) ?: listOf()
    }

    override fun getPokemonFound(): List<Pokemon> {
        return currentUser?.foundPokemonList?.toList()!!
    }

    override fun pokemonFoundByTypeCount(type: String): Int {
        return currentUser?.foundPokemonList?.map { pokemon: Pokemon ->
            pokemon.type1.uppercase() == type || pokemon.type2.uppercase() == type }?.count {
            it } ?: 0
    }

    override fun pokemonLegendaryFoundCount(): Int {
        return currentUser?.foundPokemonList?.map {
                pokemon: Pokemon ->
            pokemon.legendary == "True" }?.count {
            it
        } ?: 0
    }

    override fun attemptsCount(): Int {
        return currentUser?.scoreLog?.size ?: 0
    }

    override fun getPokedexProgress(): Int {
        val countPokemonFound = getPokemonFound().size
        return if (countPokemonFound > 0) (countPokemonFound * 100) / totalPokemon else 0
    }

    override suspend fun getAllPokemonList(): List<Pokemon> {
        return pokemonLocalRepository.getPokemonList()
    }

    override suspend fun getPokemonNotFound(): List<Pokemon> {
        return getAllPokemonList().subtract(getPokemonFound().toSet()).toMutableList()
    }

    override fun isPokedexCompleted(): Boolean {
        return currentUser?.pokedexCompleted ?: false
    }
}