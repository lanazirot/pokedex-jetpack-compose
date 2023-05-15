package com.lanazirot.pokedex.ui.screens.leaderboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lanazirot.pokedex.domain.interfaces.leaderboard.ILeaderBoardService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewModel @Inject constructor(private val leaderboardService: ILeaderBoardService) : ViewModel() {

    private val _leaderboardState = MutableStateFlow(LeaderboardState())
    val leaderboardState = _leaderboardState

    init {
        viewModelScope.launch {
            leaderboardService.getTopTen()
                .onStart {
                    _leaderboardState.value = LeaderboardState(isLoading = true)
                }.collect {
                _leaderboardState.value = LeaderboardState(leaderBoardList = it, isLoading = false)
            }
        }
    }
}