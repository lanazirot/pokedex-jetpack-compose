package com.lanazirot.pokedex.ui.screens.game

import com.lanazirot.pokedex.domain.models.Pokemon
import com.lanazirot.pokedex.domain.models.PokemonGuessable

data class GameState(
    val score: Int = 0,
    val lives: Int = 3,
    val remainingTime: Int = 5,
    val pokemonGuessable: PokemonGuessable = PokemonGuessable(pokemonAnswers = listOf())
)