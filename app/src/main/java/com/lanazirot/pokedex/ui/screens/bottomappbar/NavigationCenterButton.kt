package com.lanazirot.pokedex.ui.screens.bottomappbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.lanazirot.pokedex.R
import com.lanazirot.pokedex.ui.navigation.routing.AppRoutes
import com.lanazirot.pokedex.ui.providers.GlobalProvider

@Composable
fun NavigationCenterButton() {
    val navController = GlobalProvider.current.navigation

    FloatingActionButton(
        shape = CircleShape,
        backgroundColor = Color.Transparent,
        onClick = {
            navController.navigate(AppRoutes.Play.Game) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        //contentColor = Color.Transparent
    ) {
        Image(painter = painterResource(id = R.drawable.pokeball), contentDescription = "sisis odsmfonds", Modifier.size(60.dp))
    }
}