package com.lanazirot.pokedex.domain.models

data class User (
    var foundPokemonList :MutableList<Pokemon> = mutableListOf(),
    var scoreLog :MutableList<Score> = mutableListOf(),
    var pokedexCompleted :Boolean = false,
)