package com.lanazirot.pokedex.ui.screens.game.resultscreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lanazirot.pokedex.domain.models.GameProgressResult
import com.lanazirot.pokedex.ui.common.components.BlackButtonWithPokeball
import com.lanazirot.pokedex.ui.navigation.routing.AppRoutes
import com.lanazirot.pokedex.ui.screens.game.resultscreen.components.GameResultCard


@Composable
fun GameResultScreen(gameProgressResult: GameProgressResult) {
    Spacer(modifier = Modifier.height(16.dp))
    LazyColumn{
        items(gameProgressResult.progress) {
            GameResultCard(gameProgress = it)
        }
    }
    Row(modifier = Modifier.fillMaxWidth()){
        BlackButtonWithPokeball(
            text = "Inicio",
            route = AppRoutes.User.Profile,
            popUpTo = AppRoutes.Play.GameResult
        )
    }
}