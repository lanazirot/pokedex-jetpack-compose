package com.lanazirot.pokedex.ui.screens.game

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lanazirot.pokedex.domain.constants.GameConstants.SCORE_RATE
import com.lanazirot.pokedex.domain.interfaces.IUserManager
import com.lanazirot.pokedex.domain.models.Answer
import com.lanazirot.pokedex.domain.models.GameProgress
import com.lanazirot.pokedex.ui.screens.game.states.AnswerState
import com.lanazirot.pokedex.ui.screens.game.states.GameState
import com.lanazirot.pokedex.ui.screens.game.states.GameUIState
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


    fun startGame() {
        _gameState.value = GameState(gameUIState = GameUIState.FetchingPokemon)
        viewModelScope.launch {
            delay(500)
            fetchPokemon().collect { it ->
                _gameState.value = GameState(
                    pokemonGuessable = it,
                    gameUIState = GameUIState.PokemonFetched(it.answers.first { it.isCorrect }.pokemon)
                )
            }
        }

        _timer?.cancel()
        restartTimer()
    }

    fun restartGame() {
        _gameState.value = GameState()
    }

    suspend fun nextPokemon() {
        _gameState.value = _gameState.value.copy(gameUIState = GameUIState.FetchingPokemon)
        delay(1500)
        fetchPokemon().collect {
            _gameState.value = _gameState.value.copy(
                pokemonGuessable = it,
                gameUIState = GameUIState.PokemonFetched(it.answers.first { it.isCorrect }.pokemon),
                remainingTime = 5
            )
        }
        restartTimer()
    }

    fun guessPokemon(answer: Answer) {
        _timer?.cancel()
        val isCorrect = _gameState.value.pokemonGuessable.answers.first { it.isCorrect }.pokemon.id == answer.pokemon.id
        val gameProgress = GameProgress(_gameState.value.pokemonGuessable, isCorrect)
        if (isCorrect) {
            _gameState.value = _gameState.value.copy(
                score = _gameState.value.score + _gameState.value.remainingTime * SCORE_RATE,
                answer = AnswerState.Correct(answer = answer),
                gameUIState = GameUIState.ShowingResult
            )
            userManager.addSeenPokemon(answer.pokemon)
        } else {
            _gameState.value = _gameState.value.copy(
                lives = _gameState.value.lives - 1,
                answer = AnswerState.Incorrect(answer = answer),
                gameUIState = GameUIState.ShowingResult
            )
            if (_gameState.value.lives == 0) {
                endGame()
            }
        }

        viewModelScope.launch {
            delay(1500)
            val gameProgressResult = _gameState.value.gameProgressResult
            gameProgressResult.progress += gameProgress
            _gameState.value = _gameState.value.copy(
                answer = AnswerState.None,
                gameProgressResult = gameProgressResult
            )
            nextPokemon()
        }
    }

    fun endGame() {
        _timer?.cancel()
        _gameState.value = _gameState.value.copy(looser = true, lives = 0)
        //TODO: Navigate to end game screen and update user's stats, also show a modal with the score
    }

    private fun restartTimer() {
        _timer = object : CountDownTimer((_gameState.value.remainingTime * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _gameState.value =
                    _gameState.value.copy(remainingTime = _gameState.value.remainingTime - 1)
            }

            override fun onFinish() {
                if (_gameState.value.lives > 1) {
                    viewModelScope.launch {

                        if (_gameState.value.gameUIState != GameUIState.ShowingResult) {
                            _gameState.value =
                                _gameState.value.copy(lives = _gameState.value.lives - 1)
                        }

                        nextPokemon()
                    }
                } else {
                    endGame()
                }
            }
        }.start()
    }


    private suspend fun fetchPokemon() = flow {
        emit(userManager.getRandomUnseenPokemon())
    }

}