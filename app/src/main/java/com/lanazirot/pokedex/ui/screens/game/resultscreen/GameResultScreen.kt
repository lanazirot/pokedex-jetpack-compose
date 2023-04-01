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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lanazirot.pokedex.domain.models.GameProgressResult
import com.lanazirot.pokedex.ui.common.components.BlackButtonWithPokeball
import com.lanazirot.pokedex.ui.navigation.routing.AppRoutes
import com.lanazirot.pokedex.ui.screens.game.resultscreen.components.GameResultCard
import com.lanazirot.pokedex.ui.theme.pokemonGold


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GameResultScreen(gameProgressResult: GameProgressResult) {
    BoxWithConstraints {
        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Puntuacion final: " + gameProgressResult.score.toString(),
                        fontSize = 25.sp,
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
                BlackButtonWithPokeball(
                    text = "Inicio",
                    route = AppRoutes.User.Profile,
                    popUpTo = AppRoutes.Play.GameResult
                )
            }
        )
    }
}