package com.lanazirot.pokedex.ui.screens.leaderboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.lanazirot.pokedex.domain.models.leaderboard.Leaderboard

@Composable
fun LeaderboardCard(leader: Leaderboard, index: Int) = when (index) {
    0 -> LeaderboardCardFirstPlace(leader)
    1 -> LeaderboardCardSecondPlace(leader)
    2 -> LeaderboardCardThirdPlace(leader)
    else -> LeaderboardCardOtherPlace(leader, index)
}

@Composable
fun LeaderboardCardFirstPlace(leader: Leaderboard) {
    LeaderboardCardBase(leader, "🥇", fontSize = 19.sp)
}

@Composable
fun LeaderboardCardSecondPlace(leader: Leaderboard) {
    LeaderboardCardBase(leader, "🥈", fontSize = 16.sp)
}

@Composable
fun LeaderboardCardThirdPlace(leader: Leaderboard) {
    LeaderboardCardBase(leader, "🥉", fontSize = 13.sp)
}

@Composable
fun LeaderboardCardOtherPlace(leader: Leaderboard, index: Int) {
    LeaderboardCardBase(leader, "${index + 1}")
}

@Composable
fun LeaderboardCardBase(leader: Leaderboard, place: String, fontSize: TextUnit = 10.sp) {
    Row {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            Text(text = place, fontSize = fontSize)
            Text(text = "${leader.name} - ${leader.attempts} attempts in ${leader.elapsedTime()}", fontSize = fontSize)
            Image(
                painter = rememberAsyncImagePainter(model = "file:///android_asset/countries/${leader.country}.png"),
                contentDescription = "Flag",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

