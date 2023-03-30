package com.lanazirot.pokedex.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val gp = AppProvider(navigation = navController)
    val gup = UserProvider(currentUser = UserViewModel())

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