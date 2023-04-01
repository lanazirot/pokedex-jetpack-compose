package com.lanazirot.pokedex.ui.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lanazirot.pokedex.R
import com.lanazirot.pokedex.ui.providers.GlobalProvider
import com.lanazirot.pokedex.ui.theme.Pokemon

@Composable
fun BlackButtonWithPokeball(
    text: String,
    route: String,
    popUpTo: String
){

    val navController = GlobalProvider.current.navigation

    Column {
        OutlinedButton(
            onClick = {
                navController.navigate(route){
                    popUpTo(popUpTo){
                        inclusive = true
                        saveState = false
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp),
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = Color.Black
            )
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(10.dp),
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = Pokemon
            )
            Image(
                painter = painterResource(id = R.drawable.pokeball),
                contentDescription = "Pokeball",
                modifier = Modifier.size(50.dp)
            )
        }
    }
}