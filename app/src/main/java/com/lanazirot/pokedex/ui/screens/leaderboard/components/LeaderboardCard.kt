package com.lanazirot.pokedex.ui.screens.leaderboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
    LeaderboardCardBase(leader, "🥇", fontSize = 14.sp)
}

@Composable
fun LeaderboardCardSecondPlace(leader: Leaderboard) {
    LeaderboardCardBase(leader, "🥈", fontSize = 12.sp)
}

@Composable
fun LeaderboardCardThirdPlace(leader: Leaderboard) {
    LeaderboardCardBase(leader, "🥉", fontSize = 10.sp)
}

@Composable
fun LeaderboardCardOtherPlace(leader: Leaderboard, index: Int) {
    LeaderboardCardBase(leader, "${index + 1}")
}

@Composable
fun LeaderboardCardBase(leader: Leaderboard, place: String, fontSize: TextUnit = 12.sp) {
    Row {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Text(text = place, fontSize = 25.sp)
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "${leader.name} - \n ${leader.attempts} attempts in ${leader.elapsedTime()}", fontSize = fontSize)
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = rememberAsyncImagePainter(model = "file:///android_asset/countries/${leader.country}.png"),
                contentDescription = "Flag",
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

