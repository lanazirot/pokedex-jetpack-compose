package com.lanazirot.pokedex.ui.screens.pokedex.components

import android.graphics.Color.rgb
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.rememberAsyncImagePainter
import com.lanazirot.pokedex.domain.models.Pokemon
import com.lanazirot.pokedex.ui.theme.*

@Composable
fun PokemonFullDetailDialog(pokemon: Pokemon, onDismissRequest: () -> Unit, isVisible: Boolean) {
    Dialog (
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(570.dp)
                .padding(4.dp),
            elevation = 4.dp,
            backgroundColor = pokemonRed,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(10.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                ContentDialog(pokemon, isVisible)
                CloseButton(onClick = { onDismissRequest() })
            }
        }
    }
}

@Composable
fun ContentDialog(pokemon: Pokemon, isVisible: Boolean) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(shape = RoundedCornerShape(5.dp))
                .border(
                    width = 2.dp,
                    color = Black,
                    shape = RoundedCornerShape(5.dp)
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    //.background(Color(rgb(120, 98, 17)))
                    .background(TypeColors(pokemon.type1))
                    .fillMaxHeight()
                    .clip(shape = RoundedCornerShape(5.dp))
                    .border(
                        width = 1.dp,
                        color = Black,
                        shape = RoundedCornerShape(5.dp)
                    ),
                contentAlignment = Alignment.Center
            ){
                PokemonHeaderLabelVertical(text = if(isVisible) pokemon.name else "Desconocido")
            }

            Column(
                Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                PokemonImage(pokemon = pokemon, isVisible = isVisible)

                Spacer(modifier = Modifier.height(10.dp))

                PropertyLabel(name = "ATAQUE", value = pokemon.attack.toString(), isVisible = isVisible)
                PropertyLabel(name = "DEFENSA", value = pokemon.defense.toString(), isVisible = isVisible)
                PropertyLabel(name = "HP", value = pokemon.hp.toString(), isVisible = isVisible)
                PropertyLabel(name = "ATAQUE ESPECIAL", value = pokemon.spAtk.toString(), isVisible = isVisible)
                PropertyLabel(name = "DEFENSA ESPECIAL", value = pokemon.spDef.toString(), isVisible = isVisible)
                PropertyLabel(name = "VELOCIDAD", value = pokemon.speed.toString(), isVisible = isVisible)

                if(isVisible) {
                    Spacer(modifier = Modifier.height(25.dp))
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        PokemonTypeLabel(type = pokemon.type1)
                        if (pokemon.type2 != "") {
                            Spacer(modifier = Modifier.height(5.dp))
                            PokemonTypeLabel(type = pokemon.type2)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CloseButton(onClick : () -> Unit) {
    IconButton(
        modifier = Modifier
            .size(40.dp)
            .background(Color.Transparent),
        onClick = { onClick() }
    ) {
        Icon(Icons.Outlined.Close, "icon", tint = pokemonRed)
    }
}

@Composable
fun PropertyLabel(name: String, value: String, isVisible: Boolean) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = name + " ", modifier = Modifier.padding(4.dp).width(150.dp), fontWeight = FontWeight.Normal, fontFamily = Pokemon, fontSize = 13.sp, color = pokemonBlack)
        Text(text = if(isVisible) value else "???", modifier = Modifier.padding(4.dp),color = pokemonGold , fontWeight = FontWeight.Normal, fontFamily = Pokemon, fontSize = 13.sp)
    }
}

@Composable
fun PokemonImage(pokemon: Pokemon, isVisible: Boolean) {
    val painterBackground = rememberAsyncImagePainter(model = "file:///android_asset/images/pokeball.png")
    val painter = rememberAsyncImagePainter(model = pokemon.getPathImage())

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterBackground,
            contentDescription = "pokeball",
            modifier = Modifier
                .width(250.dp)
                .height(250.dp),
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.Center
        )

        Image(
            painter = painter,
            contentDescription = "pokemon",
            modifier = Modifier
                .size(150.dp),
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.Center,
            colorFilter = if(!isVisible) ColorFilter.tint(Black) else null,
        )
    }
}

@Preview
@Composable
fun PreviewPokemonFullDetailDialog() {
    ContentDialog(
        pokemon = Pokemon(
            id = 1,
            name = "Bulbasaur",
            type1 = "Grass",
            type2 = "Poison",
            total = 318,
            hp = 45,
            attack = 49,
            defense = 49,
            spAtk = 65,
            spDef = 65,
            speed = 45,
            generation = 1,
            legendary = "false"
        ),
        isVisible = true
    )
}