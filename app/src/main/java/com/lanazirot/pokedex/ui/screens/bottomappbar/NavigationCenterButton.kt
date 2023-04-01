package com.lanazirot.pokedex.ui.screens.bottomappbar

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.lanazirot.pokedex.R
import com.lanazirot.pokedex.ui.navigation.routing.AppRoutes
import com.lanazirot.pokedex.ui.providers.GlobalProvider
import com.lanazirot.pokedex.ui.providers.GlobalUserProvider

@Composable
fun NavigationCenterButton() {
    val navController = GlobalProvider.current.navigation
    val currentUser = GlobalUserProvider.current.currentUser
    val context = LocalContext.current

    FloatingActionButton(
        shape = CircleShape,
        backgroundColor = Color.Transparent,
        onClick = {
            if(!currentUser.isPokedexCompleted()) {
                navController.navigate(AppRoutes.Play.Game) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }else{
                Toast.makeText(context, "You have already completed the pokedex!", Toast.LENGTH_SHORT).show()
            }
        }
    ) {
        Image(painter = painterResource(id = R.drawable.pokeball), contentDescription = "Pokeball", Modifier.size(60.dp))
    }
}