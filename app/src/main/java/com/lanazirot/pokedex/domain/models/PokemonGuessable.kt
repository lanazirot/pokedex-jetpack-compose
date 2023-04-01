package com.lanazirot.pokedex.domain.models

data class PokemonGuessable(val answers: List<Answer> = listOf()){
    override fun equals(other: Any?): Boolean {
        return other is PokemonGuessable && answers == other.answers
    }
    val pokemon: Pokemon get() = answers.first { it.isCorrect }.pokemon
}