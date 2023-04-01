package com.lanazirot.pokedex.ui.screens.pokedex.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

fun TypeColors (type : String): Color {
    var fondo = Color.White
    when (type)
    {
        "Bug" -> fondo = Color(147, 196, 44 )
        "Dark" -> fondo = Color(92, 84, 100)
        "Dragon" -> fondo = Color(12, 108, 196)
        "Electric" -> fondo = Color(243, 212, 60)
        "Fairy" -> fondo = Color(236, 140, 228)
        "Fighting" -> fondo = Color(204, 68, 108)
        "Fire" -> fondo = Color(252, 156, 84 )
        "Flying" -> fondo = Color(148, 172, 220)
        "Ghost" -> fondo = Color(84, 108, 172 )
        "Grass" -> fondo = Color(100, 188, 92)
        "Ground" -> fondo = Color(220, 116, 68)
        "Ice" -> fondo = Color(116, 204, 195)
        "Normal" -> fondo = Color(147, 156, 164 )
        "Poison" -> fondo = Color(172, 108, 204)
        "Psychic" -> fondo = Color(252, 116, 116 )
        "Rock" -> fondo = Color(196, 180, 140 )
        "Steel" -> fondo = Color(92, 140, 164)
        "Water" -> fondo = Color(76, 147, 212 )
    }
    return fondo
}


@OptIn(ExperimentalTextApi::class)
@Composable
fun PokemonTypeLabel(type: String){
    val painterico = rememberAsyncImagePainter(model = "file:///android_asset/types/${type}.png")
    var fondo = TypeColors(type)

    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(15.dp))
            .border(
                width = 3.dp,
                color = Color.Black,
                shape = RoundedCornerShape(15.dp)
            )
            .background(fondo)
            .height(32.dp)
            .width(120.dp)
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterico,
            contentDescription = "Type",
            modifier = Modifier,
            alignment = Alignment.Center,
        )
        Box {
            Text(
                text = "$type ",
                color = Color.White,
                fontSize = 13.sp,
            )
        }
    }
}

@Composable
fun PokemonCountTypeLabel(type: String, count : Int){
    val painterico = rememberAsyncImagePainter(model = "file:///android_asset/types/${type}.png")
    val fondo = TypeColors(type)

    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(15.dp))
            .border(
                width = 3.dp,
                color = Color.Black,
                shape = RoundedCornerShape(15.dp)
            )
            .background(fondo)
            .height(32.dp)
            .width(120.dp)
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterico,
            contentDescription = "Type",
            modifier = Modifier,
            alignment = Alignment.Center,
        )
        Box {
            Text(
                text = "${type}:\n${count}",
                color = Color.White,
                fontSize = 10.sp,
            )
        }
    }
}
