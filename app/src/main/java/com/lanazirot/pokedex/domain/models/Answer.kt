package com.lanazirot.pokedex.domain.models

data class Answer(
    val pokemon: Pokemon,
    var isCorrect: Boolean
){
    override fun equals(other: Any?): Boolean {
        return other is Answer && pokemon == other.pokemon
    }
}