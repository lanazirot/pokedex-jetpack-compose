package com.lanazirot.pokedex.ui.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
    popUpTo: String,
    ownClick: Boolean = false,
    color: Color = Color.Black,
    onClick: () -> Unit = {},
    enabled: Boolean = true
){
    val navController = GlobalProvider.current.navigation
    Spacer(modifier = Modifier.height(15.dp))
    Column {
        OutlinedButton(
            onClick = {
                if (ownClick) {
                    onClick()
                } else {
                    navController.navigate(route){
                        popUpTo(popUpTo){
                            inclusive = true
                            saveState = false
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp),
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = color
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

@Composable
fun BlackButtonWithPokeball(
    text: String,
    route: String,
    popUpTo: String,
    color: Color = Color.Black,
) {
    BlackButtonWithPokeball(
        text = text,
        route = route,
        popUpTo = popUpTo,
        ownClick = false,
        color = color,
        enabled = true
    )
}