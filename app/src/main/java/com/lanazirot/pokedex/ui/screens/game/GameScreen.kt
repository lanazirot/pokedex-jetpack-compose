package com.lanazirot.pokedex.ui.screens.game

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.ehsanmsz.mszprogressindicator.progressindicator.BallPulseSyncProgressIndicator
import com.lanazirot.pokedex.R
import com.lanazirot.pokedex.ui.theme.Pokemon
import com.lanazirot.pokedex.ui.theme.pokemonBlue
import com.lanazirot.pokedex.ui.theme.pokemonYellow


@Composable
fun GameScreen() {

    var viewModel: GameViewModel = hiltViewModel()
    var gameState = viewModel.gameState.collectAsState().value

    LaunchedEffect(Unit){
        viewModel.startGame()
    }

    when (gameState.gameUI) {
        is GameUI.Loading, GameUI.FetchingPokemon -> {
            BallPulseSyncProgressIndicator()
        }
        is GameUI.PokemonFetched -> {
            var currentPokemonToGuess = gameState.pokemonGuessable.pokemonAnswers.first { it.isCorrect }.pokemon

            Log.d("GameScreen", "Pokemon a adivinar: $currentPokemonToGuess")

            Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Top) {
                // Barra de vidas y score
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Vidas(vidas = gameState.lives)
                    Text(text = "Score ${gameState.score}")
                }
                Spacer(modifier = Modifier.height(20.dp))
                Box(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        //Imagen adivida el Pokemon
                        Image(
                            painter = painterResource(id = R.drawable.fondo),
                            contentDescription = "",
                            Modifier.size(400.dp)
                        )
                    }
                    //Titulo Pokemon
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                            .background(Color.Transparent),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        EscribirPokemon(texto = " ??? ")
                        Image(
                            painter = rememberAsyncImagePainter(
                                model = currentPokemonToGuess.getPathImage()
                            ), contentDescription = "",
                            Modifier
                                .width(375.dp)
                                .height(95.dp)
                        )
                    }

                }
                // Espacio para abarcar toda la pantalla entre el pokemon y las opciones
                Spacer(modifier = Modifier.height(30.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column() {
                            Image(
                                painter = painterResource(id = R.drawable.opcion),
                                contentDescription = "",
                                Modifier
                                    .width(175.dp)
                                    .height(75.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.opcion),
                                contentDescription = "",
                                Modifier
                                    .width(175.dp)
                                    .height(75.dp)
                            )
                        }
                        Column() {
                            Image(
                                painter = painterResource(id = R.drawable.opcion),
                                contentDescription = "",
                                Modifier
                                    .width(175.dp)
                                    .height(75.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.opcion),
                                contentDescription = "",
                                Modifier
                                    .width(175.dp)
                                    .height(75.dp)
                            )
                        }
                    }
                }
            }
        }
        else -> {
            Log.d("GameScreen", "GameScreen ${gameState.gameUI}")
            BallPulseSyncProgressIndicator()
        }
    }
}


@Composable
fun Vidas(vidas: Int) {
    Row(modifier = Modifier.width(120.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        //Por cada vida, dibujar un corazon
        for (i in 0 until vidas) {
            Image(painter = painterResource(id = R.drawable.corazon), contentDescription = "")
        }
    }
}

@Composable
fun EscribirPokemon(texto: String) {
    Box(
        modifier = Modifier
            .height(95.dp)
            .width(375.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = texto,
                style = TextStyle(
                    color = pokemonYellow,
                    fontSize = 78.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = Pokemon
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = texto,
                style = TextStyle(
                    color = pokemonBlue,
                    fontSize = 78.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = Pokemon
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    GameScreen()
}