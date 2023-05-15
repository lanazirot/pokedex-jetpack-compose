package com.lanazirot.pokedex.domain.implementations.game

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.lanazirot.pokedex.domain.constants.GameConstants
import com.lanazirot.pokedex.domain.interfaces.game.IPokemonLocalRepository
import com.lanazirot.pokedex.domain.interfaces.game.IUserManager
import com.lanazirot.pokedex.domain.models.game.Answer
import com.lanazirot.pokedex.domain.models.game.Pokemon
import com.lanazirot.pokedex.domain.models.game.PokemonGuessable
import com.lanazirot.pokedex.domain.models.game.Score
import com.lanazirot.pokedex.domain.models.user.User
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject

class UserManager @Inject constructor(
    private val pokemonLocalRepository: IPokemonLocalRepository,
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) : IUserManager {

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

    override fun onLateInit() {
        val authenticatedUser = firebaseAuth.currentUser
        if(authenticatedUser != null) {
            firebaseFirestore.collection("users").document(authenticatedUser?.email!!).get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        currentUser = document.toObject(User::class.java)
                    }
                }.addOnFailureListener { exception ->
                    Log.d("UserManager", "Error getting documents: ", exception)
                }
        }
    }

    override fun addSeenPokemon(pokemon: Pokemon) {
        currentUser?.currentUserData?.foundPokemonList?.add(pokemon)
        if (currentUser?.currentUserData?.foundPokemonList?.size == totalPokemon) {
            currentUser?.currentUserData?.pokedexCompleted = true
        }
        //Update user data in firestore
        firebaseFirestore.collection("users").document(currentUser?.email!!).set(currentUser!!)
    }

    override suspend fun addToScoreLog(score: Int) {
        val myScore = Score(score = score, date = Date())
        currentUser?.currentUserData?.scoreLog?.add(myScore)
        //Update user data in firestore
        firebaseFirestore.collection("users").document(currentUser?.email!!).set(currentUser!!).await()
    }

    override fun getTopThreeScores(): List<Score> {
        return currentUser?.currentUserData?.scoreLog?.sortedByDescending { it.score }?.take(3)
            ?: listOf()
    }

    override fun getPokemonFound(): List<Pokemon> {
        return currentUser?.currentUserData?.foundPokemonList?.toList()!!
    }

    override fun pokemonFoundByTypeCount(type: String): Int {
        return currentUser?.currentUserData?.foundPokemonList?.map { pokemon: Pokemon ->
            pokemon.type1.uppercase() == type || pokemon.type2.uppercase() == type
        }?.count {
            it
        } ?: 0
    }

    override fun pokemonLegendaryFoundCount(): Int {
        return currentUser?.currentUserData?.foundPokemonList?.map { pokemon: Pokemon ->
            pokemon.legendary == "True"
        }?.count {
            it
        } ?: 0
    }

    override fun attemptsCount(): Int {
        return currentUser?.currentUserData?.scoreLog?.size ?: 0
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
        return currentUser?.currentUserData?.pokedexCompleted ?: false
    }
}