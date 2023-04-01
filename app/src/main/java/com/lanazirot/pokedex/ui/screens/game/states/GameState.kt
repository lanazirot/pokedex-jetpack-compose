package com.lanazirot.pokedex.ui.screens.game.states

import com.lanazirot.pokedex.domain.models.GameProgressResult
import com.lanazirot.pokedex.domain.models.PokemonGuessable

data class GameState(
    val score: Int = 0,
    val lives: Int = 3,
    val remainingTime: Int = 4,
    val pokemonGuessable: PokemonGuessable = PokemonGuessable(answers = listOf()),
    val looser: Boolean = false,
    val gameUIState: GameUIState = GameUIState.Loading,
    val answer: AnswerState = AnswerState.None,
    val pokemonResultList: List<PokemonGuessable> = listOf(),
    val gameProgressResult: GameProgressResult = GameProgressResult()
)