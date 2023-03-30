package com.lanazirot.pokedex.domain.models

data class User (
    var foundPokemonList :List<Pokemon> = emptyList(),
    var scoreLog :List<Score> = emptyList(),
    var pokedexCompleted :Boolean = false,
)