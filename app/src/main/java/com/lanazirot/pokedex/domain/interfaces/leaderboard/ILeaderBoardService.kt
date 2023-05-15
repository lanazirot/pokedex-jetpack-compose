package com.lanazirot.pokedex.domain.interfaces.leaderboard

import com.lanazirot.pokedex.domain.models.leaderboard.Leaderboard
import kotlinx.coroutines.flow.Flow

interface ILeaderBoardService {
    suspend fun getTopTen(): Flow<List<Leaderboard>>
}