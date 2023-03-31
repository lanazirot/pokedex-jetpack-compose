package com.lanazirot.pokedex.domain.implementations

import com.lanazirot.pokedex.domain.interfaces.IUserManager
import com.lanazirot.pokedex.domain.models.Pokemon
import com.lanazirot.pokedex.domain.models.PokemonGuessable
import com.lanazirot.pokedex.domain.models.User
import java.util.*
import javax.inject.Inject

class UserManager @Inject constructor() : IUserManager {
    private var currentUser: User? = null

    override fun getRandomUnseenPokemon(): PokemonGuessable {
        val pokemon: Pokemon = Pokemon()
        // val listaPokemones = getPokemonNotFound()
        // val random = Random()
        // val pokemon = listaPokemones[random.nextInt(listaPokemones.size)]
        // listaPokemones.remove(pokemon)
        // val pokemonAnswers = mutableListOf<PokemonAnswer>()
        // pokemonAnswers.add(PokemonAnswer(pokemon, true))
        // for (i in 0..2) {
        //     val pokemonAnswer = PokemonAnswer(getRandomPokemon(), false)
        //     pokemonAnswers.add(pokemonAnswer)
        // }
        // pokemonAnswers.shuffle()

        return PokemonGuessable()
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
        currentUser?.foundPokemonList?.plus(pokemon)
    }

    override fun addToScoreLog(score: Int) {

    }


}