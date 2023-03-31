package com.lanazirot.pokedex.domain.models

data class PokemonAnswer(
    val pokemon: Pokemon,
    var isCorrect: Boolean
)