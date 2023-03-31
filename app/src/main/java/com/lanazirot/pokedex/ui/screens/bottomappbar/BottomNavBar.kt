package com.lanazirot.pokedex.ui.screens.bottomappbar

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lanazirot.pokedex.ui.providers.GlobalProvider
import com.lanazirot.pokedex.ui.theme.pokemonRed

@Composable
fun BottomNavBar() {
    val navController = GlobalProvider.current.navigation

    val navItems = listOf(
        BottomNavItem.User,
        BottomNavItem.Pokedex
    )

    BottomNavigation(
        backgroundColor = pokemonRed,
    ) {
        val navBack by navController.currentBackStackEntryAsState()
        val currentRoute = navBack?.destination?.route

        navItems.forEach{
            item ->
                BottomNavigationItem(
                    icon = {Icon(
                        imageVector = item.icon,
                        contentDescription = item.title)},
                    label = { Text(text = item.title)},
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.Gray,
                    alwaysShowLabel = true,
                    selected = currentRoute == item.screen_route,
                    onClick = {
                        navController.navigate(item.screen_route) {
                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }

                            launchSingleTop = true
                            restoreState = true
                        }
                    })
        }
    }
}
