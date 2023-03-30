package com.lanazirot.pokedex.domain.models

data class Pokemon(
    val attack: Int = 0,
    val defense: Int = 0,
    val generation: Int = 0,
    val hp: Int = 0,
    val id: Int = 0,
    val legendary: String = "False",
    val name: String = "",
    val spAtk: Int = 0,
    val spDef: Int = 0,
    val speed: Int = 0,
    val total: Int = 0,
    val type1: String = "",
    val type2: String = "",
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