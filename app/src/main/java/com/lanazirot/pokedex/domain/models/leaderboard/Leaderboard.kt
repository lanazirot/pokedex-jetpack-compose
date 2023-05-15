package com.lanazirot.pokedex.domain.models.leaderboard
import com.lanazirot.pokedex.converters.elapsedTime

data class Leaderboard(
    val name: String = "",
    val score: Int = 0,
    val attempts: Int = 0,
    val time: Long = 0
){
    fun elapsedTime(): String = elapsedTime(time)
}
