package com.lanazirot.pokedex.ui.common.components.google.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lanazirot.pokedex.R
import com.lanazirot.pokedex.ui.screens.login.LoginViewModel
import com.lanazirot.pokedex.ui.theme.Pokemon

@Composable
fun GoogleSignInButton(
    text: String,
    onClick: () -> Unit = {}
) {
    Spacer(modifier = Modifier.height(15.dp))
    Column {
        OutlinedButton(
            onClick = {
                onClick()
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