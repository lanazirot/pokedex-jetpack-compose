package com.lanazirot.pokedex.ui.screens.user

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.lanazirot.pokedex.R
import com.lanazirot.pokedex.domain.enums.PokemonType
import com.lanazirot.pokedex.domain.models.game.Score
import com.lanazirot.pokedex.ui.navigation.routing.AppRoutes
import com.lanazirot.pokedex.ui.providers.GlobalProvider
import com.lanazirot.pokedex.ui.providers.GlobalUserProvider
import com.lanazirot.pokedex.ui.screens.pokedex.components.PokemonCountTypeLabel
import com.lanazirot.pokedex.ui.screens.pokedex.components.PokemonHeaderLabel
import com.lanazirot.pokedex.ui.theme.pokemonBlack
import com.lanazirot.pokedex.ui.theme.pokemonBrown
import com.lanazirot.pokedex.ui.theme.pokemonGold
import com.lanazirot.pokedex.ui.theme.pokemonRed
import com.touchlane.gridpad.GridPad
import com.touchlane.gridpad.GridPadCells
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalGlideComposeApi::class)
@SuppressLint("CheckResult")
@Composable
fun UserScreen() {
    val currentUser = GlobalUserProvider.current.currentUser
    val navController = GlobalProvider.current.navigation
    val userViewModel: UserViewModel = hiltViewModel()

    val pokemonTypes = enumValues<PokemonType>()
    val columnCount = 3
    val pokemonTypeSize = pokemonTypes.size
    var rowCount = pokemonTypeSize / columnCount
    if(pokemonTypeSize % columnCount != 0) rowCount += 1



    BoxWithConstraints {
        LazyColumn(
            modifier = Modifier.padding(16.dp),
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
                        GlideImage(model = userViewModel.getUserImage(), contentDescription = "User image", modifier = Modifier.size(100.dp))
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
                }
            }
        )
    }
}

@Composable
fun CountPokemonByType(header: String, content: Int) {
    Row(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "$header ",
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
            color = pokemonBlack
        )

        Text(
            text = content.toString(),
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
            color = pokemonBrown
        )
    }
}

@Composable
fun ScoreText(score: Score) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        Text(text = score.score.toString() + " pts. - ", fontWeight = FontWeight.Bold, color = pokemonGold, fontSize = 15.sp)
        DateFormatted(date = score.date!!)
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun DateFormatted(date: Date) = Text(SimpleDateFormat("dd/MM/yyyy").format(date))

@Composable
fun ProgressBarFromNumber(progress: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    , horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Progreso: $progress%", color = pokemonRed)
        LinearProgressIndicator(
            progress = progress.toFloat() / 100,
        )
    }
}
