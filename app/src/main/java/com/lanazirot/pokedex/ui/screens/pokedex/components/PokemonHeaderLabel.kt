package com.lanazirot.pokedex.ui.screens.pokedex.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lanazirot.pokedex.ui.theme.Pokemon
import com.lanazirot.pokedex.ui.theme.pokemonBlue
import com.lanazirot.pokedex.ui.theme.pokemonYellow

fun pokemonYellow(fontSize:Int) = TextStyle(
    color = pokemonYellow,
    fontSize = fontSize.sp,
    fontWeight = FontWeight.ExtraBold,
    fontFamily = Pokemon
)

fun pokemonBlue(fontSize: Int) = TextStyle(
    color = pokemonBlue,
    fontSize = fontSize.sp,
    fontWeight = FontWeight.Black,
    fontFamily = Pokemon
)

@Composable
fun PokemonHeaderLabel(text:String){
    Box (
        modifier = Modifier
            .height(95.dp)
            .width(320.dp))
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = text, style = pokemonYellow(49), textAlign = TextAlign.Center)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = text, style = pokemonBlue(49), textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun PokemonHeaderLabelVertical(text:String){
    Box {
        Text(
            modifier = Modifier
                .vertical()
                .rotate(-90f)
                .padding(4.dp),
            style = pokemonYellow(49),
            text = text
        )
        Text(
            modifier = Modifier
                .vertical()
                .rotate(-90f)
                .padding(4.dp),
            style = pokemonBlue(49),
            text = text
        )
    }
}

fun Modifier.vertical() =
layout {
    measurable, constraints ->
    val placeable = measurable.measure(constraints)
    layout(placeable.height, placeable.width) {
        placeable.place(
            x = -(placeable.width / 2 - placeable.height / 2),
            y = -(placeable.height / 2 - placeable.width / 2)
        )
    }
}