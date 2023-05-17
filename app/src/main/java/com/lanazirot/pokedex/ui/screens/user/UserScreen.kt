package com.lanazirot.pokedex.ui.screens.user

<<<<<<< Updated upstream
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
=======
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.LinearProgressIndicator
>>>>>>> Stashed changes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
<<<<<<< Updated upstream
=======
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
>>>>>>> Stashed changes
import androidx.compose.ui.unit.dp
import com.lanazirot.pokedex.ui.providers.GlobalUserProvider
import com.lanazirot.pokedex.ui.screens.pokedex.components.PokemonSimpleCard

@Composable
fun UserScreen() {
    val currentUser = GlobalUserProvider.current.currentUser
    val scrollState = rememberScrollState()

    BoxWithConstraints {
        Column(
            modifier = Modifier.verticalScroll(scrollState).fillMaxSize()
        ) {
            Text(text = "Este listado de pokemones no vba hehjehejesjfrhck")
        LazyColumn(
<<<<<<< Updated upstream
            modifier = Modifier.weight(weight = 1f),
            content =  {
                items(currentUser.getPokemonFound()) { p ->
                    PokemonSimpleCard(pokemon = p, isVisible = true)
=======
            modifier = Modifier.padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            content =  {
                item {
                    Row( modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        PokemonHeaderLabel(text =" Estadisticas")
                    }
                    Row( modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        GlideImage(model = userViewModel.getUserImage(), contentDescription = "User image", modifier = Modifier
                            .size(100.dp)
                            .border(200.dp, Color.Transparent, RoundedCornerShape(70.dp)))

                    }
                    if(currentUser.isPokedexCompleted()){
                        Image(
                            painter = painterResource(id = R.drawable.medallapokedexcompletada),
                            contentDescription = "Pokedex completada",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "TOP 3 Puntuaciones:", color = pokemonBlack)
                    if(currentUser.getTopThreeScores().isEmpty()){
                        Text(text = "Aun no hay puntuaciones", fontSize = 10.sp)
                    }
                }
                items(currentUser.getTopThreeScores()) {score ->
                    ScoreText(score)
                }
                item {
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = "Encontrados: " + currentUser.pokemonFoundCount() + " / " + currentUser.pokemonNotFoundCount(), color = pokemonRed)
                    Spacer(modifier = Modifier.height(15.dp))
                    GridPad(
                        cells = GridPadCells(columnCount = columnCount, rowCount = rowCount),
                        Modifier.height(300.dp)
                    ) {
                        repeat(pokemonTypeSize) {
                            item{
                                PokemonCountTypeLabel(pokemonTypes[it].name.lowercase().replaceFirstChar { it.uppercase() }, currentUser.pokemonFoundByTypeCount(pokemonTypes[it].toString()))
                            }
                        }
                    }
                }
                item {
                    CountPokemonByType(
                        header = "LEGENDARIOS",
                        content = currentUser.pokemonLegendaryFoundCount()
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = "${currentUser.attemptsCount()} partidas", fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(15.dp))
                    ProgressBarFromNumber(progress = currentUser.getPokedexProgress())
                    Button(
                        onClick = {
                            userViewModel.logout(
                                onLogoutSuccess = { success, exception ->
                                    if(exception != null)  Log.e("UserScreen", exception.message.toString())
                                    if(success){
                                        navController.navigate(AppRoutes.Login.Login){
                                            popUpTo(AppRoutes.Login.Login){
                                                inclusive = true
                                            }
                                        }
                                    }
                                }
                            )
                        }
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Sign out")
                    }
>>>>>>> Stashed changes
                }
            }
        )
//
            LazyColumn(
                modifier = Modifier.weight(weight = 1f),
            content =  {
                items(currentUser.getTopThreeScores()) { p ->
                    Text(text = p.score.toString())
                }
            }
            )
            Text(text = "pokedex completada" + currentUser.isPokedexCompleted())

            Text(text = "count enciontrados" + currentUser.pokemonFoundCount())

            Text(text = "count no encontrados" + currentUser.pokemonNotFoundCount())

            Text(text = "tipo porr.........")

            Text(text = "count legendarios" + currentUser.pokemonLegendaryFoundCount())

            Text(text = "numero de intentos" + currentUser.attemptsCount())

            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "Progreso " + currentUser.getPokedexProgress())
            Text(text = "FFFFFFFFFFIN " + currentUser.getPokedexProgress())


        }
    }
}
