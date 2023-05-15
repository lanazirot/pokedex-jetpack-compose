package com.lanazirot.pokedex.domain.models.leaderboard
import com.lanazirot.pokedex.converters.elapsedTime
import com.lanazirot.pokedex.domain.models.user.User
import java.text.SimpleDateFormat
import java.util.*

data class Leaderboard(
    val name: String = "",
    val score: Int = 0,
    val attempts: Int = 0,
    val time: Long = 0,
    val date: String = ""
){
    fun elapsedTime(): String = elapsedTime(time)

    companion object{
        fun fromUser(user: User): Leaderboard {
            val currentDate = Date()
            val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
            return Leaderboard(
                name = user.name,
                score = user.currentUserData.scoreLog.maxByOrNull { it.score }!!.score,
                attempts = user.currentUserData.attempts,
                time = user.currentUserData.playedTime,
                date = formatter.format(currentDate)
            )
        }
    }
}
