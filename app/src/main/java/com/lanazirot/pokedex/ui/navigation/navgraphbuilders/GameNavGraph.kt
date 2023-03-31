package com.lanazirot.pokedex.ui.navigation.navgraphbuilders

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lanazirot.pokedex.ui.navigation.routing.AppRoutes
import com.lanazirot.pokedex.ui.screens.game.GameScreen

fun NavGraphBuilder.GameNavGraph() {
    composable(
        route = AppRoutes.Play.Game
    ) {
        GameScreen()
    }
}