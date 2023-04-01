package com.lanazirot.pokedex.ui.screens.pokedex.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.rememberAsyncImagePainter
import com.lanazirot.pokedex.R
import com.lanazirot.pokedex.domain.models.Pokemon
import com.lanazirot.pokedex.ui.theme.*

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
                .padding(4.dp),
            elevation = 4.dp,
            backgroundColor = pokemonRed,

            ) {
            Column {
                ContentDialog(pokemon)

                Row(Modifier.padding(top = 10.dp)) {
                    OutlinedButton(
                        onClick = { onDismissRequest() },
                        Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                            .weight(1F)
                            .background(pokemonRed)
                    ) {
                        Text(
                            text = stringResource(R.string.cerrar),
                            fontWeight = FontWeight.Normal,
                            fontFamily = Pokemon,
                            color = pokemonBlack
                        )
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
            EscribirPokemon(texto = pokemon.name)
            //Text(text = pokemon.name, modifier = Modifier.padding(8.dp), fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        //Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                //.size(160.dp)
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
                    //.fillMaxWidth()
                    .size(150.dp),
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.Center
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            Modifier
                .fillMaxWidth()
            //,horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "ATAQUE", modifier = Modifier.padding(4.dp), fontWeight = FontWeight.Normal, fontFamily = Pokemon, fontSize = 15.sp, color = pokemonYellow)
            Text(text = pokemon.attack.toString(), modifier = Modifier.padding(4.dp), fontSize = 15.sp)
        }

        Row(
            Modifier
                .fillMaxWidth()
            //,horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "DEFENSA", modifier = Modifier.padding(4.dp), fontWeight = FontWeight.Normal, fontFamily = Pokemon, fontSize = 15.sp, color = pokemonYellow)
            Text(text = pokemon.defense.toString(), modifier = Modifier.padding(4.dp), fontSize = 15.sp)
        }

        Row(
            Modifier
                .fillMaxWidth()
            //,horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "HP", modifier = Modifier.padding(4.dp), fontWeight = FontWeight.Normal, fontFamily = Pokemon, fontSize = 15.sp, color = pokemonYellow)
            Text(text = pokemon.hp.toString(), modifier = Modifier.padding(4.dp), fontSize = 15.sp)
        }

        Row(
            Modifier
                .fillMaxWidth()
            //,horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "ATAQUE ESPECIAL", modifier = Modifier.padding(4.dp),  fontWeight = FontWeight.Normal, fontFamily = Pokemon,fontSize = 15.sp, color = pokemonYellow)
            Text(text = pokemon.spAtk.toString(), modifier = Modifier.padding(4.dp), fontSize = 15.sp)
        }

        Row(
            Modifier
                .fillMaxWidth()
            //,horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "DEFENSA ESPECIAL", modifier = Modifier.padding(4.dp), fontWeight = FontWeight.Normal, fontFamily = Pokemon, fontSize = 15.sp, color = pokemonYellow)
            Text(text = pokemon.spDef.toString(), modifier = Modifier.padding(4.dp), fontSize = 15.sp)
        }

        Row(
            Modifier
                .fillMaxWidth()
            //,horizontalArrangement = Arrangement.Center
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
            Texto(type = pokemon.type1)
            if(pokemon.type2 != ""){
                Spacer(modifier = Modifier.width(width = 25.dp))
                Texto(type = pokemon.type2)
            }
        }
    }
}


@Composable
fun EscribirPokemon(texto:String){
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

@OptIn(ExperimentalTextApi::class)
@Composable
fun Texto(type: String){
    var painterico = rememberAsyncImagePainter(model = "file:///android_asset/types/${type}.png")
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

    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(15.dp))
            .border(
                width = 3.dp,
                color = Color.Black,
                shape = RoundedCornerShape(15.dp)
            )
            .background(fondo),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterico,
            contentDescription = "Type",
            modifier = Modifier,
            alignment = Alignment.Center,
        )
        Box(){
            Text(
                text = "$type ",
                color = Color.White,
                fontSize = 25.sp,
            )
            Text(
                text = "$type ",
                color = Color.Black,
                style = TextStyle.Default.copy(
                    fontSize = 25.sp,
                    drawStyle = Stroke(
                        miter = 10f,
                        width = 5f,
                        join = StrokeJoin.Round
                    )
                )
            )
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