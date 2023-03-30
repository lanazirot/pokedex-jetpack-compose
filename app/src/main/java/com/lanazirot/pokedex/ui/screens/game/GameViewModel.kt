package com.lanazirot.pokedex.ui.screens.game

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import com.lanazirot.pokedex.domain.models.PokemonAnswer
import com.lanazirot.pokedex.domain.models.PokemonGuessable
import com.lanazirot.pokedex.ui.screens.user.UserViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor() : ViewModel() {
    private var _gameState: MutableStateFlow<GameState> = MutableStateFlow(GameState())
    val gameState = _gameState

    private var _timer: CountDownTimer? = null


    suspend fun startGame() {
        _gameState.value = GameState(
            score = 0,
            lives = 3,
            remainingTime = 5,
            pokemonGuessable = fetchNextPokemonGuessable()
        )
        _timer = object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _gameState.value = _gameState.value.copy(
                    remainingTime = _gameState.value.remainingTime - 1
                )
            }

            override fun onFinish() {
                _gameState.value = _gameState.value.copy(
                    remainingTime = 0
                )
            }
        }.start()
    }

    suspend fun guessPokemon(id: Int) {
        val isCorrect =
            _gameState.value.pokemonGuessable.pokemonAnswers.first { it.isCorrect }.pokemon.id == id
        if (isCorrect) {
            _gameState.value = _gameState.value.copy(
                score = _gameState.value.score + 1,
                pokemonGuessable = fetchNextPokemonGuessable()
            )
        } else {
            _gameState.value = _gameState.value.copy(
                lives = _gameState.value.lives - 1,
                pokemonGuessable = fetchNextPokemonGuessable()
            )
        }
    }

    fun endGame() {
        _gameState.value = GameState()
        _timer?.cancel()
        //TODO: Navigate to end game screen and update user's stats
    }

    suspend fun fetchNextPokemonGuessable(): PokemonGuessable {
    /*    var notFoundPokemonList = userViewModel.getNotFoundPokemon()
        val pokemon = notFoundPokemonList.random()
        notFoundPokemonList.remove(pokemon)*/

        val pokemonAnswers = mutableListOf<PokemonAnswer>()
     /*   pokemonAnswers.add(PokemonAnswer(pokemon, true))
        //Remove the pokemon from the list
        for (i in 1..3) {
            val randomPokemon = notFoundPokemonList.random()
            pokemonAnswers.add(PokemonAnswer(randomPokemon, false))
        }
        pokemonAnswers.shuffle()*/
        return PokemonGuessable(pokemonAnswers)
    }

}