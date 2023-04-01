package com.lanazirot.pokedex.ui.screens.pokedex.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lanazirot.pokedex.ui.theme.Pokemon
import com.lanazirot.pokedex.ui.theme.pokemonBlue
import com.lanazirot.pokedex.ui.theme.pokemonYellow

@Composable
fun PokemonHeaderLabel(texto:String){
    Box(modifier = Modifier
        .height(95.dp)
        .width(350.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = texto,
                style = TextStyle(
                    color = pokemonYellow,
                    fontSize = 60.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = Pokemon
                )
            )
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = texto,
                style = TextStyle(
                    color = pokemonBlue,
                    fontSize = 60.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = Pokemon
                )
            )
        }
    }
}