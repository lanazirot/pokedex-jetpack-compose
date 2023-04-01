package com.lanazirot.pokedex.ui.screens.game

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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

    LaunchedEffect(Unit) {
        viewModel.startGame()
    }

    when (gameState.gameUI) {
        is GameUI.Loading, GameUI.FetchingPokemon -> {
            BallPulseSyncProgressIndicator()
        }
        is GameUI.PokemonFetched -> {
            var currentPokemonToGuess = gameState.pokemonGuessable.answers.first { it.isCorrect }.pokemon

            Log.d("GameScreen", "Pokemon a adivinar: $currentPokemonToGuess")



//            var currentPokemonToGuess = gameState.pokemonGuessable.answers.first { it.isCorrect }.pokemon
//
//            Log.d("GameScreen", "Pokemon a adivinar: $currentPokemonToGuess")



            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Barra de vidas y score
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Vidas(vidas = gameState.lives)
                    Text(text = "Score ${gameState.score}")
                }
//                Spacer(modifier = Modifier.height(20.dp))
                Box(modifier = Modifier.size(330.dp), contentAlignment = Alignment.Center) {
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.Center
//                    ) {
                    //Imagen adivida el Pokemon
                    Image(
                        painter = painterResource(id = R.drawable.fondo),
                        contentDescription = "",
                        Modifier.fillMaxWidth()
                    )

                    Image(
                        painter = rememberAsyncImagePainter(
                            model = currentPokemonToGuess.getPathImage()
                        ), contentDescription = "",
                        modifier = Modifier.size(250.dp), colorFilter = ColorFilter.tint(
                            Color.Black
                        )
                    )
//                    }
                    //Titulo Pokemon
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                            .background(Color.Transparent),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        EscribirPokemon(texto = " ??? ")
//                        Image(
//                            painter = rememberAsyncImagePainter(
//                                model = currentPokemonToGuess.getPathImage()
//                            ), contentDescription = "",
//                            Modifier
//                                .width(375.dp)
//                                .height(95.dp)
//                        )
                    }

                }
                // Espacio para abarcar toda la pantalla entre el pokemon y las opciones
//                Spacer(modifier = Modifier.height(30.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

//                    val opcion1 = gameState.pokemonGuessable.pokemonAnswers[0].pokemon
//                    val nombre1 = opcion1.name
//                    val opcion2 = gameState.pokemonGuessable.pokemonAnswers[1].pokemon
//                    val opcion3 = gameState.pokemonGuessable.pokemonAnswers[2].pokemon
//                    val opcion4 = gameState.pokemonGuessable.pokemonAnswers[3].pokemon
//                    val nombre2 = opcion2.name
//                    val nombre3 = opcion3.name
//                    val nombre4 = opcion4.name

                    val opcion1 = gameState.pokemonGuessable.answers[0]
                    val nombre1 = opcion1.pokemon.name
                    val opcion2 = gameState.pokemonGuessable.answers[1]
                    val nombre2 = opcion2.pokemon.name
                    val opcion3 = gameState.pokemonGuessable.answers[2]
                    val nombre3 = opcion3.pokemon.name
                    val opcion4 = gameState.pokemonGuessable.answers[3]
                    val nombre4 = opcion4.pokemon.name
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column() {

                            Box() {
                                Image(
                                    painter = painterResource(id = R.drawable.opcion),
                                    contentDescription = "", modifier =
                                    Modifier
                                        .width(175.dp)
                                        .height(75.dp)
                                        .clickable {
                                            viewModel.guessPokemon(opcion1)
                                        }
                                )
                                Text(
                                    text = nombre1,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .padding(10.dp),
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = Pokemon
                                    )
                                )
                            }
                            Box() {
                                Image(
                                    painter = painterResource(id = R.drawable.opcion),
                                    contentDescription = "", modifier =
                                    Modifier
                                        .width(175.dp)
                                        .height(75.dp)
                                        .clickable {
                                            viewModel.guessPokemon(opcion2)
                                        }
                                )
                                Text(
                                    text = nombre2,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .padding(10.dp),
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = Pokemon
                                    )
                                )
                            }
                        }
                        Column() {
                            Box(){
                                Image(
                                    painter = painterResource(id = R.drawable.opcion),
                                    contentDescription = "", modifier =
                                    Modifier
                                        .width(175.dp)
                                        .height(75.dp)
                                        .clickable {
                                            viewModel.guessPokemon(opcion3)
                                        }
                                )
                                Text(
                                    text = nombre3,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .padding(10.dp),
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = Pokemon
                                    )
                                )
                            }
                            Box(){
                                Image(
                                    painter = painterResource(id = R.drawable.opcion),
                                    contentDescription = "", modifier =
                                    Modifier
                                        .width(175.dp)
                                        .height(75.dp)
                                        .clickable {
                                            viewModel.guessPokemon(opcion4)
                                        }
                                )
                                Text(
                                    text = nombre4,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .padding(10.dp),
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = Pokemon
                                    )
                                )
                            }

                            Image(
                                painter = painterResource(id = R.drawable.opcion),
                                contentDescription = "", modifier =
                                Modifier
                                    .width(175.dp)
                                    .height(75.dp)
                                    .clickable { }
                            )
                            Image(
                                painter = painterResource(id = R.drawable.opcion),
                                contentDescription = "", modifier =
                                Modifier
                                    .width(175.dp)
                                    .height(75.dp)
                                    .clickable { }
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