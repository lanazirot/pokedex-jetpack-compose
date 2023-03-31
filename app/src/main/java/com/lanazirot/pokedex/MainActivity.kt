package com.lanazirot.pokedex

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.lanazirot.pokedex.ui.navigation.AppNavGraph
import com.lanazirot.pokedex.ui.providers.AppProvider
import com.lanazirot.pokedex.ui.providers.GlobalProvider
import com.lanazirot.pokedex.ui.providers.GlobalUserProvider
import com.lanazirot.pokedex.ui.providers.UserProvider
import com.lanazirot.pokedex.ui.screens.bottomappbar.BottomNavBar
import com.lanazirot.pokedex.ui.screens.user.UserViewModel
import com.lanazirot.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val userViewModel: UserViewModel by viewModels()
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            val gp = AppProvider(navigation = navController)
            val gup = UserProvider(currentUser = userViewModel)

            PokedexTheme {
                CompositionLocalProvider(
                    GlobalProvider provides gp
                ) {
                    CompositionLocalProvider(
                        GlobalUserProvider provides gup
                    ) {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            Scaffold(
                                bottomBar = {
                                    BottomNavBar()
                                },
                                content = {
                                    AppNavGraph(globalProvider = gp)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

/*
@Composable
fun Greeting() {
    val painter = rememberAsyncImagePainter(model = "file:///android_asset/images/019.png")
    Image(
        painter = painter,
        contentDescription = "Imagen con filtro negro",
        colorFilter = ColorFilter.tint(Color.Black)
    )
}

*/