package com.lanazirot.pokedex.domain.models

import android.net.Uri
import com.google.gson.Gson

data class GameProgress(val pokemonGuessable: PokemonGuessable, var wasCorrect: Boolean){
    override fun equals(other: Any?): Boolean {
        return other is GameProgress && pokemonGuessable.answers.first { it.isCorrect }.pokemon == other.pokemonGuessable.answers.first { it.isCorrect }.pokemon
    }
}
data class GameProgressResult(var progress: List<GameProgress> = mutableListOf(), var score: Int = 0){
    override fun toString(): String = Uri.encode(Gson().toJson(this))
    fun removeRepeatedGameProgresses(): List<GameProgress> {
        val progresses = mutableListOf<GameProgress>()
        progresses.addAll(progress)
        progresses.removeAll {
            !it.wasCorrect && progresses.count { progress -> progress.pokemonGuessable == it.pokemonGuessable } > 1
        }
        return progresses
    }
}