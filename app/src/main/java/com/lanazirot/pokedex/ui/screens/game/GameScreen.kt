package com.lanazirot.pokedex.ui.screens.game

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.ehsanmsz.mszprogressindicator.progressindicator.BallPulseSyncProgressIndicator
import com.lanazirot.pokedex.R
import com.lanazirot.pokedex.domain.models.game.Answer
import com.lanazirot.pokedex.ui.navigation.routing.AppRoutes
import com.lanazirot.pokedex.ui.providers.GlobalProvider
import com.lanazirot.pokedex.ui.screens.game.states.AnswerState
import com.lanazirot.pokedex.ui.screens.game.states.GameUIState
import com.lanazirot.pokedex.ui.screens.pokedex.components.PokemonHeaderLabel
import com.lanazirot.pokedex.ui.theme.Pokemon
import com.lanazirot.pokedex.ui.theme.pokemonBlue


@Composable
fun GameScreen() {

    var viewModel: GameViewModel = hiltViewModel()
    var gameState = viewModel.gameState.collectAsState().value
    val navController = GlobalProvider.current.navigation


    LaunchedEffect(Unit) {
        viewModel.startGame()
    }

    LaunchedEffect(gameState.looser) {
        if (gameState.looser) {

            navController.navigate(AppRoutes.Play.gameResult(gameState.gameProgressResult.toString())) {
                popUpTo(AppRoutes.User.Profile) {
                    inclusive = true
                }
            }
        }
    }

    when (gameState.gameUIState) {
        is GameUIState.Loading, GameUIState.FetchingPokemon -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                BallPulseSyncProgressIndicator(
                    color = pokemonBlue
                )
            }

        }
        is GameUIState.PokemonFetched, GameUIState.ShowingResult -> {
            var currentPokemonToGuess =
                gameState.pokemonGuessable.answers.first { it.isCorrect }.pokemon

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
                    Text(text = "Score ${gameState.score}", fontFamily = Pokemon, fontSize = 20.sp)
                }
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Time ${
                            String.format(
                                "%02d:%02d",
                                gameState.remainingTime / 60,
                                gameState.remainingTime % 60
                            )
                        }", fontFamily = Pokemon, fontSize = 9.sp
                    )
                }
                Box(
                    modifier = Modifier
                        //.size(430.dp)
                        .fillMaxWidth(), contentAlignment = Alignment.Center
                ) {


                    Image(
                        painter = painterResource(id = R.drawable.fondo),
                        contentDescription = "",
                        Modifier
                            .fillMaxWidth()
                            .size(420.dp)
                    )

                    Image(
                        painter = rememberAsyncImagePainter(model = currentPokemonToGuess.getPathImage()),
                        contentDescription = "",
                        modifier = Modifier.size(250.dp),
                        colorFilter = when (gameState.answer) {
                            is AnswerState.Correct -> null
                            is AnswerState.Incorrect, AnswerState.TimeOut -> ColorFilter.tint(Color.Red)
                            else -> ColorFilter.tint(Color.Black)
                        }
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                            .background(Color.Transparent),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        when (gameState.answer) {
                            is AnswerState.Correct -> {
                                PokemonHeaderLabel(text = " " + currentPokemonToGuess.name + " ")
                            }
                            is AnswerState.Incorrect, AnswerState.TimeOut -> {
                                PokemonHeaderLabel(text = " ... ")
                            }
                            else -> {
                                PokemonHeaderLabel(text = " ??? ")
                            }
                        }
                    }

                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    val opcion1 = gameState.pokemonGuessable.answers[0]
                    val nombre1 = opcion1.pokemon.name
                    val opcion2 = gameState.pokemonGuessable.answers[1]
                    val nombre2 = opcion2.pokemon.name
                    val opcion3 = gameState.pokemonGuessable.answers[2]
                    val nombre3 = opcion3.pokemon.name
                    val opcion4 = gameState.pokemonGuessable.answers[3]
                    val nombre4 = opcion4.pokemon.name


                    //Loop trough gameState.pokemonGuessable.answers (include index)

                    /*

                    Al que vea esto modifiquelo asi por favor ajja salu2 - Alan P

                    for ((index, answer) in gameState.pokemonGuessable.answers.withIndex()) {

                    }*/

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column {

                            Box {
                                Image(
                                    painter = paintButtonIfCorrect(gameState.answer, opcion1),
                                    contentDescription = "", modifier = Modifier
                                        .width(175.dp)
                                        .height(75.dp)
                                        .clickable(
                                            enabled = gameState.gameUIState != GameUIState.ShowingResult && gameState.answer != AnswerState.TimeOut
                                        ) {
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
                            Box {
                                Image(
                                    painter = paintButtonIfCorrect(gameState.answer, opcion2),
                                    contentDescription = "", modifier = Modifier
                                        .width(175.dp)
                                        .height(75.dp)
                                        .clickable(
                                            enabled = gameState.gameUIState != GameUIState.ShowingResult && gameState.answer != AnswerState.TimeOut
                                        ) {
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
                        Column {
                            Box {

                                Image(
                                    painter = paintButtonIfCorrect(gameState.answer, opcion3),
                                    contentDescription = "", modifier = Modifier
                                        .width(175.dp)
                                        .height(75.dp)
                                        .clickable(
                                            enabled = gameState.gameUIState != GameUIState.ShowingResult && gameState.answer != AnswerState.TimeOut
                                        ) {
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
                            Box {
                                Image(
                                    painter = paintButtonIfCorrect(gameState.answer, opcion4),
                                    contentDescription = "", modifier = Modifier
                                        .width(175.dp)
                                        .height(75.dp)
                                        .clickable(
                                            enabled = gameState.gameUIState != GameUIState.ShowingResult && gameState.answer != AnswerState.TimeOut
                                        ) {
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

                        }
                    }
                }
            }
        }
        else -> {
            Log.d("GameScreen", "GameScreen ${gameState.gameUIState}")
            BallPulseSyncProgressIndicator()
        }
    }
}

@Composable
private fun paintButtonIfCorrect(answer: AnswerState, pokemonFromPressedButton: Answer): Painter {
    return when (answer) {
        is AnswerState.Correct -> painterResource(if (pokemonFromPressedButton == answer.answer) R.drawable.correcto else R.drawable.opcion)
        is AnswerState.Incorrect -> painterResource(if (pokemonFromPressedButton == answer.answer) R.drawable.incorrecto else R.drawable.opcion)
        else -> painterResource(R.drawable.opcion)
    }
}

@Composable
private fun Vidas(vidas: Int) {
    Row(modifier = Modifier.width(120.dp), horizontalArrangement = Arrangement.Start) {
        //Por cada vida, dibujar un corazon
        for (i in 0 until vidas) {
            Image(painter = painterResource(id = R.drawable.corazon), contentDescription = "")
        }
    }
}
