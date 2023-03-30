package com.lanazirot.pokedex.ui.screens.pokedex.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.lanazirot.pokedex.R
import com.lanazirot.pokedex.domain.models.Pokemon
import com.lanazirot.pokedex.ui.theme.pokemonRed

@Composable
fun PokemonSimpleCard(pokemon: Pokemon, isVisible: Boolean = false) {
    val painter = rememberAsyncImagePainter(model = pokemon.getPathImage())
    val imageHeight = 90.dp

    Card (
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(120.dp)
    ) {
        Row (modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            if(isVisible) { //Ya descubrio el pokemon, muestra los datos
                VisibleCard(painter = painter, pokemon = pokemon, modifier = Modifier.size(imageHeight))
            } else { //No se muestran los datos
                HiddenCard(painter = painter, pokemon = pokemon, modifier = Modifier.size(imageHeight))
            }
        }
    }
}

@Composable
fun HiddenCard(painter: AsyncImagePainter, pokemon: Pokemon, modifier: Modifier) {
    Image(
        painter = painter,
        contentDescription = pokemon.name,
        colorFilter = ColorFilter.tint(Color.Black),
        modifier = modifier
    )
    Column(modifier = Modifier.padding(16.dp)) {
        HeaderTextCard(text = getFormatedIdPokemon(pokemon.id) + " - " + stringResource(R.string.desconocido))
    }
}

@Composable
fun VisibleCard(painter: AsyncImagePainter, pokemon: Pokemon, modifier: Modifier) {
    Image(
        painter = painter,
        contentDescription = pokemon.name,
        modifier = modifier
    )
    Row (
        modifier = Modifier.padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        HeaderTextCard(text = getFormatedIdPokemon(pokemon.id) + " - " + pokemon.name)
        BotonVerPokemon(pokemon = pokemon)
    }
}

@Composable
fun BotonVerPokemon(pokemon: Pokemon) {
    var showCustomDialog by remember { mutableStateOf(false) }

    IconButton(
        modifier = Modifier.size(30.dp)
            .background(pokemonRed),
        onClick = { showCustomDialog = !showCustomDialog }
    ) {
        Icon(Icons.Outlined.Add, "icon", tint = Color.White)
    }

    if (showCustomDialog) {
        PokemonFullDetailDialog(
            pokemon = pokemon,
            onDismissRequest = { showCustomDialog = !showCustomDialog }
        )
    }
}

@Composable
fun HeaderTextCard(text: String) {
    Text(text = text, fontWeight = FontWeight.Bold, fontSize = 20.sp)
}

fun getFormatedIdPokemon(id: Int): String {
    return id.toString().padStart(3, '0')
}

@Preview
@Composable
fun PokemonSimpleCardPreview() {
    PokemonSimpleCard(pokemon = Pokemon( id = 3, name = "Venusaur", type1 = "Grass", type2 = "Poison", total = 525, hp = 80, attack = 82, defense = 83, spAtk = 100, spDef = 100, speed = 80, generation = 1, legendary = "False"))
}