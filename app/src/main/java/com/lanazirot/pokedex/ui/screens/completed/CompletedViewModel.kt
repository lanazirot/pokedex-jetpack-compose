package com.lanazirot.pokedex.ui.screens.completed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lanazirot.pokedex.domain.interfaces.game.IUserManager
import com.lanazirot.pokedex.domain.models.user.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompletedViewModel @Inject constructor(
    private val userManager: IUserManager
) : ViewModel() {
    private val _userState = MutableStateFlow(userManager.getUser())
    val user :StateFlow<User> = _userState

    private val _status = MutableStateFlow(CompletedUIState())
    val status :StateFlow<CompletedUIState> = _status

    fun setCountry(country: String) {
        _status.value = _status.value.copy(isValid = true)
        userManager.setCountry(country)
    }

    fun saveUserInLeaderboard() {
        viewModelScope.launch {
            userManager.addToLeaderboard()
        }
    }
}