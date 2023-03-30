package com.lanazirot.pokedex.ui.providers

import android.annotation.SuppressLint
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController

data class AppProvider (
    val navigation: NavHostController
)

@SuppressLint("CompositionLocalNaming")
val GlobalProvider = compositionLocalOf<AppProvider> { error("No navigation host controller provided.") }