package com.lanazirot.pokedex.ui.screens.game.resultscreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.lanazirot.pokedex.domain.models.GameProgress
import com.lanazirot.pokedex.ui.screens.pokedex.components.PokemonCard

@Composable
fun GameResultCard(gameProgress: GameProgress) {
    val correctPokemon = gameProgress.pokemonGuessable.answers.first { it.isCorrect }.pokemon

    val painter = rememberAsyncImagePainter(model = correctPokemon.getPathImage())
    val imageHeight = 90.dp

    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(250.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (!gameProgress.wasCorrect) {
                PokemonCard(
                    painter = painter,
                    pokemon = correctPokemon,
                    modifier = Modifier.size(imageHeight),
                    isVisible = false
                )
            } else {
                PokemonCard(
                    painter = painter,
                    pokemon = correctPokemon,
                    modifier = Modifier.size(imageHeight),
                    isVisible = true
                )
            }
        }
    }
}
