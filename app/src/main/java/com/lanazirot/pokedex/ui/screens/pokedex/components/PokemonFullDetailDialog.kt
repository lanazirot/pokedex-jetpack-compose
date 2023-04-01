package com.lanazirot.pokedex.ui.screens.pokedex.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.rememberAsyncImagePainter
import com.lanazirot.pokedex.domain.models.Pokemon
import com.lanazirot.pokedex.ui.screens.game.EscribirPokemon
import com.lanazirot.pokedex.ui.theme.Pokemon
import com.lanazirot.pokedex.ui.theme.pokemonRed
import com.lanazirot.pokedex.ui.theme.pokemonYellow

@Composable
fun CloseButton(onClick : () -> Unit) {
    IconButton(
        modifier = Modifier.size(40.dp)
            .background(Color.White),
        onClick = { onClick() }
    ) {
        Icon(Icons.Outlined.Close, "icon", tint = pokemonRed)
    }
}

@Composable
fun PokemonFullDetailDialog(pokemon: Pokemon, onDismissRequest: () -> Unit) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(598.dp)
                .padding(4.dp),
            elevation = 4.dp,
            backgroundColor = pokemonRed,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                horizontalAlignment = Alignment.End
            ) {
                CloseButton(onClick = { onDismissRequest() })
                ContentDialog(pokemon)
            }
        }
    }
}

@Composable
fun ContentDialog(pokemon: Pokemon) {
    val painter = rememberAsyncImagePainter(model = pokemon.getPathImage())
    val painterBackground = rememberAsyncImagePainter(model = "file:///android_asset/images/pokeball.png")

    Column(
        Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            EscribirPokemon(texto = pokemon.name)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterBackground,
                contentDescription = "pokeball",
                modifier = Modifier.width(250.dp).height(250.dp),
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.Center
            )

            Image(
                painter = painter,
                contentDescription = "pokemon",
                modifier = Modifier
                    .size(150.dp),
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.Center
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            Modifier
                .fillMaxWidth()
        ) {
            Text(text = "ATAQUE", modifier = Modifier.padding(4.dp), fontWeight = FontWeight.Normal, fontFamily = Pokemon, fontSize = 15.sp, color = pokemonYellow)
            Text(text = pokemon.attack.toString(), modifier = Modifier.padding(4.dp), fontSize = 15.sp)
        }

        Row(
            Modifier
                .fillMaxWidth()
        ) {
            Text(text = "DEFENSA", modifier = Modifier.padding(4.dp), fontWeight = FontWeight.Normal, fontFamily = Pokemon, fontSize = 15.sp, color = pokemonYellow)
            Text(text = pokemon.defense.toString(), modifier = Modifier.padding(4.dp), fontSize = 15.sp)
        }

        Row(
            Modifier
                .fillMaxWidth()
        ) {
            Text(text = "HP", modifier = Modifier.padding(4.dp), fontWeight = FontWeight.Normal, fontFamily = Pokemon, fontSize = 15.sp, color = pokemonYellow)
            Text(text = pokemon.hp.toString(), modifier = Modifier.padding(4.dp), fontSize = 15.sp)
        }

        Row(
            Modifier
                .fillMaxWidth()
        ) {
            Text(text = "ATAQUE ESPECIAL", modifier = Modifier.padding(4.dp),  fontWeight = FontWeight.Normal, fontFamily = Pokemon,fontSize = 15.sp, color = pokemonYellow)
            Text(text = pokemon.spAtk.toString(), modifier = Modifier.padding(4.dp), fontSize = 15.sp)
        }

        Row(
            Modifier
                .fillMaxWidth()
        ) {
            Text(text = "DEFENSA ESPECIAL", modifier = Modifier.padding(4.dp), fontWeight = FontWeight.Normal, fontFamily = Pokemon, fontSize = 15.sp, color = pokemonYellow)
            Text(text = pokemon.spDef.toString(), modifier = Modifier.padding(4.dp), fontSize = 15.sp)
        }

        Row(
            Modifier
                .fillMaxWidth()
        ) {
            Text(text = "VELOCIDAD", modifier = Modifier.padding(4.dp), fontWeight = FontWeight.Normal, fontFamily = Pokemon, fontSize = 15.sp, color = pokemonYellow)
            Text(text = pokemon.speed.toString(), modifier = Modifier.padding(4.dp), fontSize = 15.sp)
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            PokemonTypeLabel(type = pokemon.type1)
            if(pokemon.type2 != ""){
                Spacer(modifier = Modifier.width(width = 25.dp))
                PokemonTypeLabel(type = pokemon.type2)
            }
        }
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
        )
    )
}