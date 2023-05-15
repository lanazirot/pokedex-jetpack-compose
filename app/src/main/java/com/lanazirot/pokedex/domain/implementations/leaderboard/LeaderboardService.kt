package com.lanazirot.pokedex.domain.implementations.leaderboard

import com.google.firebase.firestore.FirebaseFirestore
import com.lanazirot.pokedex.domain.interfaces.leaderboard.ILeaderBoardService
import com.lanazirot.pokedex.domain.models.leaderboard.Leaderboard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LeaderboardService @Inject constructor(private val firebaseFirestore: FirebaseFirestore) : ILeaderBoardService {
    override suspend fun getTopTen(): Flow<MutableList<Leaderboard>> {
        val topTen =  firebaseFirestore.collection("leaderboard")
            .orderBy("time")
            .orderBy("attempts")
            .limit(10)
            .get()
            .await()
            .toObjects(Leaderboard::class.java)

        return flow { emit(topTen) }
    }
}