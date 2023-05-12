package com.lanazirot.pokedex.domain.models.game

import com.google.gson.annotations.SerializedName

data class PokemonMapped(
    @SerializedName("id")
    val id: Int,
    val attack: Int,
    val defense: Int,
    val generation: Int,
    val hp: Int,
    val legendary: String,
    val name: String,
    val spAtk: Int,
    val spDef: Int,
    val speed: Int,
    val total: Int,
    val type1: String,
    val type2: String
)

fun PokemonMapped.toPokemon(): Pokemon {
    return Pokemon(
        attack = attack,
        defense = defense,
        generation = generation,
        hp = hp,
        id = id,
        legendary = legendary,
        name = name,
        spAtk = spAtk,
        spDef = spDef,
        speed = speed,
        total = total,
        type1 = type1,
        type2 = type2
    )
}
