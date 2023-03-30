package com.lanazirot.pokedex.ui.screens.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter


@Composable
fun GameScreen() {
    var viewModel: GameViewModel by viewModel()
    var gameState = viewModel.gameState.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.startGame()
    }

    LaunchedEffect(gameState) {
        if (gameState.remainingTime == 0) {
            viewModel.endGame()
        }
    }

    Column {
        Row {
            //For each live in lives, show a heart
            (0 until gameState.lives).forEach {
                Text("❤️")
            }
        }

        Row {
            //Image should be here, centered
            val painter =
                rememberAsyncImagePainter(model = gameState.pokemonGuessable.pokemonAnswers.filter { it.isCorrect }[0].pokemon.getPathImage())
            Image(
                painter = painter,
                contentDescription = "Imagen con filtro negro",
                colorFilter = ColorFilter.tint(Color.Black)
            )
            Text("?????")
        }

        Row {
            Column {
                OutlinedButton(onClick = {}) {
                    gameState.pokemonGuessable.pokemonAnswers[0].pokemon.name
                }
            }
            Column {
                OutlinedButton(onClick = {}) {
                    gameState.pokemonGuessable.pokemonAnswers[1].pokemon.name
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            Column {
                OutlinedButton(onClick = {}) {
                    gameState.pokemonGuessable.pokemonAnswers[2].pokemon.name
                }
            }
            Column {
                OutlinedButton(onClick = {}) {
                    gameState.pokemonGuessable.pokemonAnswers[3].pokemon.name
                }
            }
        }


    }

}


@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    GameScreen()
}