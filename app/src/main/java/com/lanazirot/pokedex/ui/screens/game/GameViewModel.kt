package com.lanazirot.pokedex.ui.screens.game

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lanazirot.pokedex.domain.interfaces.IUserManager
import com.lanazirot.pokedex.domain.models.PokemonGuessable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(private val userManager: IUserManager) : ViewModel() {
    private var _gameState: MutableStateFlow<GameState> = MutableStateFlow(GameState())
    val gameState = _gameState

    private var _timer: CountDownTimer? = null

    private val MULTIPLICADOR_PUNTUACION = 10

    fun startGame() {
        _gameState.value = GameState( gameUI = GameUI.FetchingPokemon )
        viewModelScope.launch {
            delay(1000)
            fetchPokemon().collect {
                _gameState.value = GameState(
                    pokemonGuessable = it,
                    gameUI = GameUI.PokemonFetched(it.pokemonAnswers.first { it.isCorrect }.pokemon)
                )
            }
        }

       // _timer?.cancel()
       // restartTimer()
    }

    fun restartGame(){
        _gameState.value = GameState()
    }

    suspend fun nextPokemon() {
        _gameState.value = _gameState.value.copy(
            pokemonGuessable = fetchNextPokemonGuessable(),
            remainingTime = 5
        )
        restartTimer()
    }

    fun guessPokemon(id: Int) {
        val isCorrect = _gameState.value.pokemonGuessable.pokemonAnswers.first { it.isCorrect }.pokemon.id == id
        if (isCorrect) {
            _gameState.value = _gameState.value.copy(
                score =  _gameState.value.remainingTime * MULTIPLICADOR_PUNTUACION ,
            )
        } else {
            _gameState.value = _gameState.value.copy( lives = _gameState.value.lives - 1, )
            if (_gameState.value.lives == 0) {
                endGame()
            }
        }
    }

    fun endGame() {
        _timer?.cancel()
        _gameState.value = _gameState.value.copy(looser = true, lives = 0)
        //TODO: Navigate to end game screen and update user's stats
    }

    fun fetchNextPokemonGuessable(): PokemonGuessable {
//        val pokemonAnswers = mutableListOf<PokemonAnswer>()
//        val correctPokemon = userManager.getRandomUnseenPokemon()
//        pokemonAnswers.add(PokemonAnswer(correctPokemon, true))
//        //Agregar otras 3 opciones incorrectas
//        for (i in 0 until 3) {
//            val incorrectPokemon = userManager.getRandomUnseenPokemon()
//            pokemonAnswers.add(PokemonAnswer(incorrectPokemon, false))
//        }
//
//
//        pokemonAnswers.shuffle()
//        return PokemonGuessable(pokemonAnswers)
        return PokemonGuessable(
            listOf(
            )
        )
    }

    private fun restartTimer() {
        _timer = object : CountDownTimer((_gameState.value.remainingTime * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _gameState.value = _gameState.value.copy(remainingTime = _gameState.value.remainingTime - 1)
            }

            override fun onFinish() {
                if (_gameState.value.lives > 1) {
                    viewModelScope.launch {
                        nextPokemon()
                    }
                } else {
                    endGame()
                }
            }
        }.start()
    }

    private suspend fun fetchNextPokemonGuessable(): PokemonGuessable {
        return userManager.getRandomUnseenPokemon()
    }

    private suspend fun fetchPokemon() = flow {
        emit(userManager.getRandomUnseenPokemon())
    }

}