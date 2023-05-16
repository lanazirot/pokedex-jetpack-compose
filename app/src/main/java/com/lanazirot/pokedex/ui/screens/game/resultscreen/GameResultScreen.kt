package com.lanazirot.pokedex.ui.screens.game.resultscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lanazirot.pokedex.domain.models.game.GameProgressResult
import com.lanazirot.pokedex.ui.common.components.BlackButtonWithPokeball
import com.lanazirot.pokedex.ui.navigation.routing.AppRoutes
import com.lanazirot.pokedex.ui.providers.GlobalUserProvider
import com.lanazirot.pokedex.ui.screens.game.resultscreen.components.GameResultCard
import com.lanazirot.pokedex.ui.theme.pokemonGold


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GameResultScreen(gameProgressResult: GameProgressResult) {
    val currentUser = GlobalUserProvider.current.currentUser

    BoxWithConstraints {
        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, top = 25.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Puntuacion final: " + gameProgressResult.score.toString(),
                        fontSize = 20.sp,
                        color = pokemonGold
                    )
                }
            },
            content = {
                LazyColumn(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = {
                        item {

                        }
                        items(gameProgressResult.progress) {
                            GameResultCard(gameProgress = it)
                        }
                    }
                )
            },
            bottomBar = {
                if(currentUser.isPokedexCompleted()) {
                    BlackButtonWithPokeball(
                        text = "Convertirse en maestro pokemon!",
                        route = AppRoutes.Play.Completed,
                        popUpTo = AppRoutes.Play.GameResult,
                        color = Color.Red
                    )
                } else {
                    BlackButtonWithPokeball(
                        text = "Inicio",
                        route = AppRoutes.User.Profile,
                        popUpTo = AppRoutes.Play.GameResult
                    )
                }
            }
        )
    }
}