package com.lanazirot.pokedex.ui.screens.user

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lanazirot.pokedex.R
import com.lanazirot.pokedex.domain.enums.PokemonType
import com.lanazirot.pokedex.domain.models.Score
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

@Composable
fun UserScreen() {
    val currentUser = GlobalUserProvider.current.currentUser
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
                    Text(text = "Numero de partidas jugadas: " + currentUser.attemptsCount(), fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(15.dp))
                    ProgressBarFromNumber(progress = currentUser.getPokedexProgress())
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
            text = header + " ",
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
fun DateFormatted(date: Date) {
    Text(SimpleDateFormat("dd/MM/yyyy").format(date))
}

@Composable
fun ProgressBarFromNumber(progress: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    , horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Progreso: " + progress + "%", color = pokemonRed)
        LinearProgressIndicator(
            progress = progress.toFloat() / 100,
        )
    }
}
