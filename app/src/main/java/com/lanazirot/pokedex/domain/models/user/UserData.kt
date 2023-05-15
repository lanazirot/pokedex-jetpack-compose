package com.lanazirot.pokedex.domain.models.user

import com.lanazirot.pokedex.domain.models.game.Pokemon
import com.lanazirot.pokedex.domain.models.game.Score

data class UserData(
    var foundPokemonList: MutableList<Pokemon> = mutableListOf(),
    var scoreLog: MutableList<Score> = mutableListOf(),
    var pokedexCompleted: Boolean = false,
    var attempts: Int = 0,
    var playedTime: Long = 0,
)