package com.lanazirot.pokedex.ui.screens.leaderboard.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lanazirot.pokedex.ui.screens.leaderboard.LeaderboardViewModel
import com.lanazirot.pokedex.ui.screens.pokedex.components.PokemonHeaderLabel
import androidx.compose.foundation.lazy.itemsIndexed
import com.ehsanmsz.mszprogressindicator.progressindicator.BallPulseSyncProgressIndicator

@Composable
fun LeaderboardList() {
    val leaderboardViewModel: LeaderboardViewModel = hiltViewModel()
    val leaderboardState = leaderboardViewModel.leaderboardState.collectAsState()

    if (leaderboardState.value.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            BallPulseSyncProgressIndicator()
        }
        return
    }


    BoxWithConstraints {
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        PokemonHeaderLabel(text = " Leaderboard")
                    }
                }
                itemsIndexed(leaderboardState.value.leaderBoardList) { index, leader ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                       LeaderboardCard(leader, index)
                    }
                }
            }
        )
    }
}