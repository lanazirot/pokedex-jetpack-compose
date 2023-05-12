package com.lanazirot.pokedex.domain.models.user

import com.lanazirot.pokedex.domain.models.game.Pokemon
import com.lanazirot.pokedex.domain.models.game.Score

data class User (
    var foundPokemonList :MutableList<Pokemon> = mutableListOf(),
    var scoreLog :MutableList<Score> = mutableListOf(),
    var pokedexCompleted :Boolean = false,
)