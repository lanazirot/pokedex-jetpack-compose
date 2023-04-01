package com.lanazirot.pokedex.domain.models

data class GameProgress(val pokemonGuessable: PokemonGuessable, var wasCorrect: Boolean)
data class GameProgressResult(var progress: List<GameProgress> = mutableListOf(), var score: Int = 0)