package com.lanazirot.pokedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.lanazirot.pokedex.ui.navigation.navgraphbuilders.LoginNavGraph
import com.lanazirot.pokedex.ui.navigation.navgraphbuilders.PokedexNavGraph
import com.lanazirot.pokedex.ui.navigation.navgraphbuilders.UserNavGraph
import com.lanazirot.pokedex.ui.navigation.routing.AppRoutes
import com.lanazirot.pokedex.ui.providers.AppProvider


@Composable
fun AppNavGraph(globalProvider: AppProvider) {
    val navController = globalProvider.navigation

    NavHost(navController = navController, startDestination = AppRoutes.Login.Login) {
        LoginNavGraph()
        UserNavGraph()
        PokedexNavGraph()
    }
}