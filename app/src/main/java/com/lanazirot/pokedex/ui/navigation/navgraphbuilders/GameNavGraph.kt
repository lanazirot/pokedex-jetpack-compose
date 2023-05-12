package com.lanazirot.pokedex.ui.navigation.navgraphbuilders

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.lanazirot.pokedex.domain.models.game.GameProgressResult
import com.lanazirot.pokedex.ui.navigation.navgraphbuilders.navtypes.ProfileArgType
import com.lanazirot.pokedex.ui.navigation.routing.AppRoutes
import com.lanazirot.pokedex.ui.screens.game.GameScreen
import com.lanazirot.pokedex.ui.screens.game.resultscreen.GameResultScreen

fun NavGraphBuilder.GameNavGraph() {
    composable(
        route = AppRoutes.Play.Game
    ) {
        GameScreen()
    }
    composable(
        route = AppRoutes.Play.GameResult,
        arguments = listOf(navArgument("gameProgressResult") { type = ProfileArgType() })
    ) {
        val gameResult = Gson().fromJson(it.arguments?.getString("gameProgressResult"), GameProgressResult::class.java)
        GameResultScreen(gameProgressResult = gameResult)
    }
}