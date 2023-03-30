package com.lanazirot.pokedex.ui.providers

import android.annotation.SuppressLint
import androidx.compose.runtime.compositionLocalOf
import com.lanazirot.pokedex.ui.screens.user.UserViewModel

data class UserProvider (
    val currentUser: UserViewModel
)

@SuppressLint("CompositionLocalNaming")
val GlobalUserProvider = compositionLocalOf<UserProvider> { error("No user provider provided.") }