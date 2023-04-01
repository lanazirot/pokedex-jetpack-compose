package com.lanazirot.pokedex.domain.models

import android.net.Uri
import com.google.gson.Gson

data class GameProgress(val pokemonGuessable: PokemonGuessable, var wasCorrect: Boolean)
data class GameProgressResult(var progress: List<GameProgress> = mutableListOf(), var score: Int = 0){
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}