package com.lanazirot.pokedex.domain.models.user

import com.lanazirot.pokedex.converters.elapsedTime
import com.lanazirot.pokedex.domain.models.game.Pokemon
import com.lanazirot.pokedex.domain.models.game.Score

data class UserData (
    var foundPokemonList :MutableList<Pokemon> = mutableListOf(),
    var scoreLog :MutableList<Score> = mutableListOf(),
    var pokedexCompleted :Boolean = false,
    val playedTime: Long = 0,
){
    fun getTotalAttempts(): Int = scoreLog.size
    fun getPlayedTime(): String = elapsedTime(playedTime)
}