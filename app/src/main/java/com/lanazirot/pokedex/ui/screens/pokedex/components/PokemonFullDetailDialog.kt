package com.lanazirot.pokedex.ui.screens.pokedex.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.rememberAsyncImagePainter
import com.lanazirot.pokedex.R
import com.lanazirot.pokedex.domain.models.Pokemon

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
                .padding(8.dp),
            elevation = 8.dp,
            backgroundColor = Color.White,

        ) {
            Column {
                ContentDialog(pokemon)

                Row(Modifier.padding(top = 10.dp)) {
                    OutlinedButton(
                        onClick = { onDismissRequest() },
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F)
                    ) {
                        Text(text = stringResource(R.string.cerrar))
                    }
                }
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
            Text(text = pokemon.name, modifier = Modifier.padding(8.dp), fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Box(
            modifier = Modifier
                .size(160.dp)
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterBackground,
                contentDescription = "pokeball",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.Center
            )

            Image(
                painter = painter,
                contentDescription = "pokemon",
                modifier = Modifier.fillMaxSize()
                    .size(10.dp),
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.Center
            )
        }

        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "ATAQUE:", modifier = Modifier.padding(8.dp), fontSize = 20.sp, color = Color.Yellow)
            Text(text = pokemon.attack.toString(), modifier = Modifier.padding(8.dp), fontSize = 20.sp)
        }

        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "DEFENSA:", modifier = Modifier.padding(8.dp), fontSize = 20.sp, color = Color.Yellow)
            Text(text = pokemon.defense.toString(), modifier = Modifier.padding(8.dp), fontSize = 20.sp)
        }

        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "HP:", modifier = Modifier.padding(8.dp), fontSize = 20.sp, color = Color.Yellow)
            Text(text = pokemon.hp.toString(), modifier = Modifier.padding(8.dp), fontSize = 20.sp)
        }

        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "ATAQUE ESPECIAL:", modifier = Modifier.padding(8.dp), fontSize = 20.sp, color = Color.Yellow)
            Text(text = pokemon.spAtk.toString(), modifier = Modifier.padding(8.dp), fontSize = 20.sp)
        }

        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "DEFENSA ESPECIAL:", modifier = Modifier.padding(8.dp), fontSize = 20.sp, color = Color.Yellow)
            Text(text = pokemon.spDef.toString(), modifier = Modifier.padding(8.dp), fontSize = 20.sp)
        }

        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "VELOCIDAD:", modifier = Modifier.padding(8.dp), fontSize = 20.sp, color = Color.Yellow)
            Text(text = pokemon.speed.toString(), modifier = Modifier.padding(8.dp), fontSize = 20.sp)
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = pokemon.type1, modifier = Modifier.padding(8.dp), fontSize = 20.sp, color = Color.Green)
            Text(text = pokemon.type2, modifier = Modifier.padding(8.dp), fontSize = 20.sp, color = Color.Red)
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