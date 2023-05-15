package com.lanazirot.pokedex.ui.screens.game

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lanazirot.pokedex.domain.constants.GameConstants.SCORE_RATE
import com.lanazirot.pokedex.domain.interfaces.game.IUserManager
import com.lanazirot.pokedex.domain.models.game.Answer
import com.lanazirot.pokedex.domain.models.game.GameProgress
import com.lanazirot.pokedex.ui.screens.game.states.AnswerState
import com.lanazirot.pokedex.ui.screens.game.states.GameState
import com.lanazirot.pokedex.ui.screens.game.states.GameUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(private val userManager: IUserManager) : ViewModel() {
    private var _gameState: MutableStateFlow<GameState> = MutableStateFlow(GameState())
    val gameState = _gameState
    private var _timer: CountDownTimer? = null


    fun startGame() {
        _gameState.value = GameState(gameUIState = GameUIState.FetchingPokemon)
        viewModelScope.launch {
            userManager.addAttempt()
            fetchPokemon().collect { it ->
                _gameState.value = GameState(
                    pokemonGuessable = it,
                    gameUIState = GameUIState.PokemonFetched(it.answers.first { it.isCorrect }.pokemon)
                )
            }
            _timer?.cancel()
            restartTimer()
        }
    }


    fun nextPokemon() {
        _gameState.value =
            _gameState.value.copy(gameUIState = GameUIState.FetchingPokemon, remainingTime = 5)
        viewModelScope.launch {
            delay(700)
            fetchPokemon().collect { it ->
                _gameState.value = _gameState.value.copy(
                    pokemonGuessable = it,
                    gameUIState = GameUIState.PokemonFetched(it.answers.first { it.isCorrect }.pokemon),
                    answer = AnswerState.None,
                    remainingTime = 5
                )
            }
            _timer?.cancel()
            restartTimer()
        }
    }

    fun guessPokemon(answer: Answer) {
        _timer?.cancel()
        val isCorrect =
            _gameState.value.pokemonGuessable.answers.first { it.isCorrect }.pokemon.id == answer.pokemon.id
        val gameProgress = GameProgress(_gameState.value.pokemonGuessable, isCorrect)

        val currentProgress = mutableListOf<GameProgress>()
        currentProgress.addAll(_gameState.value.gameProgressResult.progress)
        if (currentProgress.contains(gameProgress)) {
            currentProgress.remove(gameProgress)
            _gameState.value.gameProgressResult.progress = currentProgress
        }

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
            addProgress(gameProgress)
            delay(1200)
            nextPokemon()
        }
    }

    private fun addProgress(gameProgress: GameProgress) {
        val gameProgressResult = _gameState.value.gameProgressResult
        gameProgressResult.progress += gameProgress
        _gameState.value = _gameState.value.copy(gameProgressResult = gameProgressResult)
    }

    fun endGame() {
        _timer?.cancel()
        _gameState.value.gameProgressResult.score = _gameState.value.score
        viewModelScope.launch {
            userManager.addToScoreLog(_gameState.value.score, _gameState.value.elapsedTimeInSeconds)
            delay(1200)
            _gameState.value = _gameState.value.copy(
                looser = true,
                lives = 0,
                gameUIState = GameUIState.ShowingResult
            )
        }
    }


    private fun restartTimer() {
        _timer = object : CountDownTimer(6000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _gameState.value = _gameState.value.copy(
                    remainingTime = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished).toInt(),
                    elapsedTimeInSeconds = _gameState.value.elapsedTimeInSeconds + 1
                )
            }
            override fun onFinish() {
                addProgress(GameProgress(_gameState.value.pokemonGuessable, false))
                if (_gameState.value.lives > 1) {
                    viewModelScope.launch {
                        _gameState.value = _gameState.value.copy(
                            lives = _gameState.value.lives - 1,
                            answer = AnswerState.TimeOut,
                        )
                        delay(1200)
                        nextPokemon()
                    }
                } else {
                    endGame()
                }
            }
        }.start()
    }

    private suspend fun fetchPokemon() = flow {
        try {
            emit(userManager.getRandomUnseenPokemon())
        } catch (e: Exception) {
            endGame()
        }
    }

}