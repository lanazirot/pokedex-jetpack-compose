package com.lanazirot.pokedex.ui.screens.pokedex.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.lanazirot.pokedex.R
import com.lanazirot.pokedex.domain.models.Pokemon

@Composable
fun PokemonSimpleCard(pokemon: Pokemon, isVisible: Boolean = false) {
    val painter = rememberAsyncImagePainter(model = pokemon.getPathImage())
    var showCustomDialog by remember { mutableStateOf(false) }
    val imageHeight = 90.dp

    Card (
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(8.dp)
            .height(200.dp)
            .clickable(onClick = { if(isVisible) showCustomDialog = !showCustomDialog })
    ) {
        Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            PokemonCard(painter = painter, pokemon = pokemon, modifier = Modifier.size(imageHeight), isVisible = isVisible)
        }
    }

    if (showCustomDialog) {
        PokemonFullDetailDialog(
            pokemon = pokemon,
            onDismissRequest = { showCustomDialog = !showCustomDialog },
            isVisible = isVisible
        )
    }
}

@Composable
fun PokemonCard(painter: AsyncImagePainter, pokemon: Pokemon, modifier: Modifier, isVisible: Boolean) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painter,
            contentDescription = pokemon.name,
            colorFilter = if(!isVisible) ColorFilter.tint(Color.Black) else null,
            modifier = modifier
        )

        HeaderTextCard(text = getFormatedIdPokemon(pokemon.id) + " - " + if(isVisible) pokemon.name else stringResource(R.string.desconocido))
    }
}

@Composable
fun HeaderTextCard(text: String) {
    Text(text = text, fontWeight = FontWeight.Normal, fontSize = 12.sp, textAlign = TextAlign.Center, color = Color.Black)
}

fun getFormatedIdPokemon(id: Int): String {
    return id.toString().padStart(3, '0')
}

@Preview
@Composable
fun PokemonSimpleCardPreview() {
    PokemonSimpleCard(pokemon = Pokemon( id = 3, name = "Venusaur", type1 = "Grass", type2 = "Poison", total = 525, hp = 80, attack = 82, defense = 83, spAtk = 100, spDef = 100, speed = 80, generation = 1, legendary = "False"))
}