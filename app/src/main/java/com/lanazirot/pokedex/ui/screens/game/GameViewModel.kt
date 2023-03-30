package com.lanazirot.pokedex.ui.screens.game

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import com.lanazirot.pokedex.domain.interfaces.IPokemonGuessService
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class GameViewModel @Inject constructor() : ViewModel() {
    private var _gameState: MutableStateFlow<GameState> = MutableStateFlow(GameState())
    val gameState = _gameState

    private var _timer: CountDownTimer? = null


    suspend fun startGame() {
        _gameState.value = GameState(
            score = 0,
            lives = 3,
            remainingTime = 5,
            pokemonGuessable =
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
        val isCorrect = false
        if (isCorrect) {
            _gameState.value = _gameState.value.copy(
                score = _gameState.value.score + 1,
                pokemonGuessable = IPokemonGuessService.getGuessablePokemon()
            )
        } else {
            _gameState.value = _gameState.value.copy(
                lives = _gameState.value.lives - 1,
                pokemonGuessable = IPokemonGuessService.getGuessablePokemon()
            )
        }
    }

    fun endGame() {
        _gameState.value = GameState()
        _timer?.cancel()
        //TODO: Navigate to end game screen and update user's stats
    }


}