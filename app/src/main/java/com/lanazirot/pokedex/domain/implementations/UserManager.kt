package com.lanazirot.pokedex.domain.implementations

import com.lanazirot.pokedex.domain.interfaces.IPokemonLocalRepository
import com.lanazirot.pokedex.domain.interfaces.IUserManager
import com.lanazirot.pokedex.domain.models.*
import java.util.*
import javax.inject.Inject

class UserManager @Inject constructor(
    private val pokemonLocalRepository: IPokemonLocalRepository
) : IUserManager {
    private val totalPokemon = 151
    private var currentUser: User? = null

    override suspend fun getRandomUnseenPokemon(): PokemonGuessable {
        val pokemonNotFoundList = getPokemonNotFound()
        val random = Random()

        //De los pokemones no encontrados, traigo 4 al azar
        val randomPokemonList = mutableListOf<PokemonAnswer>()
        for (i in 0..3) {
            val randomPokemon = pokemonNotFoundList[random.nextInt(pokemonNotFoundList.size)]
            randomPokemonList.add(PokemonAnswer(pokemon = randomPokemon, isCorrect = false))
        }
        randomPokemonList[3].isCorrect = true

        //Mezclo la lista de pokemon
        randomPokemonList.shuffle()

        return PokemonGuessable(pokemonAnswers = randomPokemonList)
    }

    init {
        currentUser = User()
        currentUser?.foundPokemonList = mutableListOf(Pokemon(id = 1, name = "Bulbasaur", type1 = "Grass", type2 = "Poison", legendary = "False"), Pokemon(id = 4, name = "Charmander", type1 = "Fire", type2 = "", legendary = "False"), Pokemon(id = 7, name = "Squirtle", type1 = "Water", type2 = "", legendary = "False"), Pokemon(id = 25, name = "Pikachu", type1 = "Electric", type2 = "", legendary = "False"), Pokemon(id = 133, name = "Eevee", type1 = "Normal", type2 = "", legendary = "False"), Pokemon(id = 150, name = "Mewtwo", type1 = "Psychic", type2 = "", legendary = "True"))
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
        val myScore = Score(score = score, date = System.currentTimeMillis().toString())
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
            pokemon.type1 == type || pokemon.type2 == type }?.count {
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