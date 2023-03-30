package com.lanazirot.pokedex.ui.providers

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController

data class AppProvider (
    val navigation: NavHostController
)

val GlobalProvider = compositionLocalOf<AppProvider> { error("No navigation host controller provided.") }