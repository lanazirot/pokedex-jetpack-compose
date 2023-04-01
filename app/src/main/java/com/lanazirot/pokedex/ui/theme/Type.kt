package com.lanazirot.pokedex.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.lanazirot.pokedex.R

val Pokemon = FontFamily(
    Font(R.font.wonder, FontWeight.Normal),
    Font(R.font.pokemonhollow, FontWeight.Black),
    Font(R.font.pokemonsolid, FontWeight.ExtraBold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = Pokemon,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    )
)