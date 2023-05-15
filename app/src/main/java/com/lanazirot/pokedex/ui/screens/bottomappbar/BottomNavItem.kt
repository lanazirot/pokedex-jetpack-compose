package com.lanazirot.pokedex.ui.screens.bottomappbar

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import com.lanazirot.pokedex.R
import com.lanazirot.pokedex.ui.navigation.routing.AppRoutes

sealed class BottomNavItem(
    var title: String,
    @DrawableRes var icon: Int,
    var screen_route: String
) {
    object User : BottomNavItem("Home", R.drawable.profile, AppRoutes.User.Profile)
    object Pokedex : BottomNavItem("Pokedex", R.drawable.pokedex, AppRoutes.Pokedex.PokemonList)
    object Leaderboard : BottomNavItem("Leaderboard", R.drawable.leaderboard , AppRoutes.Leaderboard.Leaderboard)
}