package com.lanazirot.pokedex.domain.models

data class Pokemon(
    val attack: Int,
    val defense: Int,
    val generation: Int,
    val hp: Int,
    val id: Int,
    val legendary: String,
    val name: String,
    val spAtk: Int,
    val spDef: Int,
    val speed: Int,
    val total: Int,
    val type1: String,
    val type2: String
){
    fun getPathImage(): String {
        return "file:///android_asset/images/${id.toString().padStart(3, '0')}.png"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pokemon

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}