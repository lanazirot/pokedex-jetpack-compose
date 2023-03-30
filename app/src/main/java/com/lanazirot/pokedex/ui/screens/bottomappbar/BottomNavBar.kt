package com.lanazirot.pokedex.ui.screens.bottomappbar

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
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