package com.lanazirot.pokedex.ui.screens.leaderboard

import com.lanazirot.pokedex.domain.models.leaderboard.Leaderboard

data class LeaderboardState(
    val leaderBoardList: List<Leaderboard> = emptyList(),
    val isLoading: Boolean = false
)