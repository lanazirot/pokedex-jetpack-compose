package com.lanazirot.pokedex.ui.screens.bottomappbar

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.lanazirot.pokedex.ui.providers.GlobalProvider

@Composable
fun BottomNavBar() {
    BottomAppBar(
        modifier = Modifier
            .height(65.dp)
            .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)),
        cutoutShape = CircleShape,
        elevation = 22.dp
    ) {
        NavBar()
    }
}

@Composable
fun NavBar() {
    val navController = GlobalProvider.current.navigation

    val navItems = listOf(
        BottomNavItem.User,
        BottomNavItem.Pokedex,
        BottomNavItem.Leaderboard
    )

    val navBack by navController.currentBackStackEntryAsState()
    val currentRoute = navBack?.destination?.route
    BottomNavigation(
        modifier = Modifier
            .padding(12.dp, 0.dp, 12.dp, 0.dp)
            .height(100.dp),
        elevation = 0.dp
    ) {
        navItems.forEach{
                item ->
            BottomNavigationItem(
                icon = {Icon(
                    imageVector = ImageVector.vectorResource(id = item.icon),
                    contentDescription = item.title,
                    modifier = Modifier.size(30.dp)
                )},
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