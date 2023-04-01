package com.lanazirot.pokedex.ui.screens.user

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lanazirot.pokedex.domain.enums.PokemonType
import com.lanazirot.pokedex.domain.models.Score
import com.lanazirot.pokedex.ui.providers.GlobalUserProvider
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
            content =  {
                item {
                    Text(text="Mis estadisticas")
                    if(currentUser.isPokedexCompleted()){
                        Text(text="Has completado la pokedex")//TODO cambiar por imagen
                    }
                }
                items(currentUser.getTopThreeScores()) { score ->
                    ScoreText(score)
                }
                item {
                    Text(text = "Encontrados: " + currentUser.pokemonFoundCount() + " / " + currentUser.pokemonNotFoundCount())

                    GridPad(
                        cells = GridPadCells(columnCount = columnCount, rowCount = rowCount),
                        Modifier.height(300.dp)
                    ) {
                        repeat(pokemonTypeSize) {
                            item {
                                CountPokemonByType(
                                    header = pokemonTypes[it].name,
                                    content = currentUser.pokemonFoundByTypeCount(pokemonTypes[it].toString())
                                )
                            }
                        }
                    }
                }
                item {
                    CountPokemonByType(
                        header = "LEGENDARIOS",
                        content = currentUser.pokemonLegendaryFoundCount()
                    )
                    Text(text = "Numero de partidas jugadas: " + currentUser.attemptsCount())
                    ProgressBarFromNumber(progress = currentUser.getPokedexProgress())
                }
            }
        )
    }
}

@Composable
fun CountPokemonByType(header: String, content: Int) {
    Row(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = header + ": ",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = content.toString(),
            fontSize = 16.sp,
        )
    }
}

@Composable
fun ScoreText(score: Score) {
    Row {
        Text(text = score.score.toString() + " - ", fontWeight = FontWeight.Bold)
        DateFormatted(date = score.date!!)
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun DateFormatted(date: Date) {
    Text(SimpleDateFormat("dd/MM/yyyy HH:mm").format(date))
}

@Composable
fun ProgressBarFromNumber(progress: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    , horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Progreso: " + progress + "%")
        LinearProgressIndicator(
            progress = progress.toFloat() / 100,
        )
    }
}
