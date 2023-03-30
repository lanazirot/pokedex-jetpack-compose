package com.lanazirot.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.lanazirot.pokedex.ui.navigation.AppNavGraph
import com.lanazirot.pokedex.ui.providers.AppProvider
import com.lanazirot.pokedex.ui.providers.GlobalProvider
import com.lanazirot.pokedex.ui.screens.LoginScreen
import com.lanazirot.pokedex.ui.screens.MainScreen
import com.lanazirot.pokedex.ui.theme.PokedexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun Greeting() {
    val painter = rememberAsyncImagePainter(model = "file:///android_asset/images/019.png")
    Image(
        painter = painter,
        contentDescription = "Imagen con filtro negro",
        colorFilter = ColorFilter.tint(Color.Black)
    )
}

