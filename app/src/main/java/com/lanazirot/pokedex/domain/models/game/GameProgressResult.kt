package com.lanazirot.pokedex.domain.models.game

import android.net.Uri
import com.google.gson.Gson

data class GameProgress(val pokemonGuessable: PokemonGuessable, var wasCorrect: Boolean){
    override fun equals(other: Any?): Boolean {
        return other is GameProgress && pokemonGuessable.answers.first { it.isCorrect }.pokemon == other.pokemonGuessable.answers.first { it.isCorrect }.pokemon
    }
}
data class GameProgressResult(var progress: List<GameProgress> = mutableListOf(), var score: Int = 0){
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}