package com.lanazirot.pokedex.domain.models

data class Answer(
    val pokemon: Pokemon,
    var isCorrect: Boolean
)